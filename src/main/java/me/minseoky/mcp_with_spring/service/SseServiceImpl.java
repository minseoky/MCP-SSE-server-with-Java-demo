package me.minseoky.mcp_with_spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.minseoky.mcp_with_spring.dto.McpRequest;
import me.minseoky.mcp_with_spring.dto.commonComponents.Prompt;
import me.minseoky.mcp_with_spring.dto.commonComponents.Tool;
import me.minseoky.mcp_with_spring.dto.initialize.InitializeResponse;
import me.minseoky.mcp_with_spring.dto.promptsList.PromptsListResponse;
import me.minseoky.mcp_with_spring.dto.toolsCall.ToolsCallResponse;
import me.minseoky.mcp_with_spring.dto.toolsCall.component.ToolsCallContent;
import me.minseoky.mcp_with_spring.dto.toolsList.ToolsListResponse;
import me.minseoky.mcp_with_spring.repository.PromptRepository;
import me.minseoky.mcp_with_spring.repository.SessionRepository;
import me.minseoky.mcp_with_spring.repository.ToolRepository;
import me.minseoky.mcp_with_spring.support.sse.SseEventProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SseServiceImpl implements SseService{

    @Value("${spring.application.name}")
    private String SERVER_NAME;
    @Value("${spring.application.version}")
    private String SERVER_VERSION;
    @Value("${mcp.protocol-version}")
    private String MCP_PROTOCOL_VERSION;
    @Value("${mcp.capabilities.prompts.list-changed}")
    private Boolean PROMPTS_LIST_CHANGED;
    @Value("${mcp.capabilities.resources.subscribe}")
    private Boolean RESOURCES_SUBSCRIBE;
    @Value("${mcp.capabilities.resources.list-changed}")
    private Boolean RESOURCES_LIST_CHANGED;
    @Value("${mcp.capabilities.tools.list-changed}")
    private Boolean TOOLS_LIST_CHANGED;

    private final SessionRepository sessionRepository;
    private final PromptRepository promptRepository;
    private final ToolRepository toolRepository;

    @Override
    public SseEmitter createConnection() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        String sessionId = UUID.randomUUID().toString().replace("-", "");

        sessionRepository.save(sessionId, emitter);
        
        try {
            emitter.send(SseEventProvider.createNavigateEndpointEvent(sessionId));
        } catch (Exception e) {
            log.error("❌ SSE 연결 이벤트 전송 실패: {}", e.getMessage());
            sessionRepository.remove(sessionId);
            emitter.complete();
        }


        // 연결 종료 및 오류 처리
        emitter.onCompletion(() -> {
            log.info("🔌 SSE 연결 종료: sessionId={}", sessionId);
            sessionRepository.remove(sessionId);
        });

        emitter.onError((ex) -> {
            log.error("❌ SSE 연결 오류: sessionId={}, error={}", sessionId, ex.getMessage());
            sessionRepository.remove(sessionId);
        });

        emitter.onTimeout(() -> {
            log.warn("⏰ SSE 연결 타임아웃: sessionId={}", sessionId);
            sessionRepository.remove(sessionId);
        });

        return emitter;
    }

    @Override
    public ResponseEntity<Void> initialize(McpRequest request, String sessionId) {
        Integer id = request.getId();
        SseEmitter emitter = sessionRepository.getEmitter(sessionId);
        InitializeResponse response = InitializeResponse.of(
            id,
            MCP_PROTOCOL_VERSION,
            PROMPTS_LIST_CHANGED,
            RESOURCES_SUBSCRIBE,
            RESOURCES_LIST_CHANGED,
            TOOLS_LIST_CHANGED,
            SERVER_NAME,
            SERVER_VERSION
        );

        try {
            emitter.send(SseEventProvider.createInitResponseEvent(response));
        } catch (Exception e) {
            log.error("❌ Init 이벤트 전송 실패: {}", e.getMessage());
            sessionRepository.remove(sessionId);
            emitter.complete();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity<Void> completeInitialize(McpRequest request, String sessionId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity<Void> promptsList(McpRequest request, String sessionId) {
        Integer id = request.getId();
        SseEmitter emitter = sessionRepository.getEmitter(sessionId);
        List<Prompt> prompts = promptRepository.getAllPrompts();

        PromptsListResponse response = PromptsListResponse.of(
            id,
            prompts
        );

        try {
            emitter.send(SseEventProvider.createPromptsListResponseEvent(response));
        } catch (Exception e) {
            log.error("❌ PromptsList 이벤트 전송 실패: {}", e.getMessage());
            sessionRepository.remove(sessionId);
            emitter.complete();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity<Void> toolsList(McpRequest request, String sessionId) {
        Integer id = request.getId();
        SseEmitter emitter = sessionRepository.getEmitter(sessionId);
        List<Tool> tools = toolRepository.getAllTools();

        ToolsListResponse response = ToolsListResponse.of(
            id,
            tools
        );

        try {
            emitter.send(SseEventProvider.createToolsListEvent(response));
        } catch (Exception e) {
            log.error("❌ ToolsList 이벤트 전송 실패: {}", e.getMessage());
            sessionRepository.remove(sessionId);
            emitter.complete();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Override
    public ResponseEntity<Void> toolsCall(McpRequest request, String sessionId) {
        Integer id = request.getId();
        SseEmitter emitter = sessionRepository.getEmitter(sessionId);
        String toolName = request.getParams().get("name").toString();
        ToolsCallContent content = toolRepository.callTool(toolName);

        ToolsCallResponse response = ToolsCallResponse.of(
            id,
            content
        );

        try {
            emitter.send(SseEventProvider.createToolsCallEvent(response));
        } catch (Exception e) {
            log.error("❌ ToolsCall 이벤트 전송 실패: {}", e.getMessage());
            sessionRepository.remove(sessionId);
            emitter.complete();
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}

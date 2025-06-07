package me.minseoky.mcp_with_spring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.minseoky.mcp_with_spring.dto.McpRequest;
import me.minseoky.mcp_with_spring.service.SseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class McpController {

    private final SseService sseService;

    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter connect() {
        return sseService.createConnection();
    }

    @PostMapping(path = "/mcp/messages",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> mcpMessage(
        @RequestBody
        McpRequest request,
        @RequestParam(value = "sessionId", defaultValue = "default")
        String sessionId
    ) {
        log.info("MCP Request: {}", request);
        String method = request.getMethod();
        return switch (method) {
            case "initialize" -> sseService.initialize(request, sessionId);
            case "notifications/initialized" -> sseService.completeInitialize(request, sessionId);
            case "prompts/list" -> sseService.promptsList(request, sessionId);
            case "tools/list" -> sseService.toolsList(request, sessionId);
            case "tools/call" -> sseService.toolsCall(request, sessionId);
            default -> ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        };
    }
}

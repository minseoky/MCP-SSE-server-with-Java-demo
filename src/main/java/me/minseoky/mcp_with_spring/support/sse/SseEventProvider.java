package me.minseoky.mcp_with_spring.support.sse;

import me.minseoky.mcp_with_spring.dto.initialize.InitializeResponse;
import me.minseoky.mcp_with_spring.dto.promptsList.PromptsListResponse;
import me.minseoky.mcp_with_spring.dto.toolsCall.ToolsCallResponse;
import me.minseoky.mcp_with_spring.dto.toolsList.ToolsListResponse;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseEventProvider {

    /**
     * MCP 클라이언트에게 MCP 서버의 엔드포인트를 노출하는 이벤트 생성
     */
    static SseEmitter.SseEventBuilder createNavigateEndpointEvent(String sessionId) {
        return SseEmitter.event()
            .id(sessionId)
            .name("endpoint")
            .data("/mcp/messages?sessionId=" + sessionId);
    }

    /**
     * MCP 클라이언트의 Init 요청에 응답하는 이벤트 생성
     */
    static SseEmitter.SseEventBuilder createInitResponseEvent(InitializeResponse response) {
        return SseEmitter.event()
            .name("message")
            .data(response, MediaType.APPLICATION_JSON)
            .reconnectTime(100);
    }

    /**
     * MCP 클라이언트의 Prompt 리스트 요청에 응답하는 이벤트 생성
     */
    static SseEmitter.SseEventBuilder createPromptsListResponseEvent(PromptsListResponse response) {
        return SseEmitter.event()
            .name("message")
            .data(response, MediaType.APPLICATION_JSON)
            .reconnectTime(100);
    }

    /**
     * MCP 클라이언트의 Tool 리스트 요청에 응답하는 이벤트 생성
     */
    static SseEmitter.SseEventBuilder createToolsListEvent(ToolsListResponse response) {
        return SseEmitter.event()
            .name("message")
            .data(response, MediaType.APPLICATION_JSON)
            .reconnectTime(100);
    }

    /**
     * MCP 클라이언트의 Tool 호출 요청에 응답하는 이벤트 생성
     */
    static SseEmitter.SseEventBuilder createToolsCallEvent(ToolsCallResponse response) {
        return SseEmitter.event()
            .name("message")
            .data(response, MediaType.APPLICATION_JSON)
            .reconnectTime(100);
    }


}

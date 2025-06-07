package me.minseoky.mcp_with_spring.service;

import me.minseoky.mcp_with_spring.dto.McpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseService {

    SseEmitter createConnection();

    ResponseEntity<Void> initialize(McpRequest request, String sessionId);

    ResponseEntity<Void> completeInitialize(McpRequest request, String sessionId);

    ResponseEntity<Void> promptsList(McpRequest request, String sessionId);

    ResponseEntity<Void> toolsList(McpRequest request, String sessionId);

    ResponseEntity<Void> toolsCall(McpRequest request, String sessionId);
}

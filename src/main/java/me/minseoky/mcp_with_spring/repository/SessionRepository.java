package me.minseoky.mcp_with_spring.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SessionRepository {

    void save(String sessionId, SseEmitter emitter);

    void remove(String sessionId);

    SseEmitter getEmitter(String sessionId);
}

package me.minseoky.mcp_with_spring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemorySessionRepository implements SessionRepository{

    // InMemorySessionRepository
    private final ConcurrentMap<String, SseEmitter> clients = new ConcurrentHashMap<>();


    @Override
    public void save(String sessionId, SseEmitter emitter) {
        clients.put(sessionId, emitter);
    }

    @Override
    public void remove(String sessionId) {
        clients.remove(sessionId);
    }

    @Override
    public SseEmitter getEmitter(String sessionId) {
        return clients.get(sessionId);
    }
}

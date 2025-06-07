package me.minseoky.mcp_with_spring.repository;

import me.minseoky.mcp_with_spring.dto.commonComponents.Prompt;

import java.util.List;

public interface PromptRepository {

    List<Prompt> getAllPrompts();

}

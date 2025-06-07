package me.minseoky.mcp_with_spring.repository;

import lombok.RequiredArgsConstructor;
import me.minseoky.mcp_with_spring.dto.commonComponents.Prompt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DummyPromptRepository implements PromptRepository{

    private final List<Prompt> prompts = List.of(
        Prompt.builder()
            .name("languagePrompt")
            .description("Model should be spoke in this language")
            .arguments(List.of(
                // empty
            ))
            .build()
    );

    @Override
    public List<Prompt> getAllPrompts() {
        return prompts;
    }
}

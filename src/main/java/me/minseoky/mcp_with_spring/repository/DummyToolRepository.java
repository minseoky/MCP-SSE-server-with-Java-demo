package me.minseoky.mcp_with_spring.repository;

import ch.qos.logback.core.joran.sanity.Pair;
import lombok.RequiredArgsConstructor;
import me.minseoky.mcp_with_spring.dto.commonComponents.Tool;
import me.minseoky.mcp_with_spring.dto.toolsCall.component.ToolsCallContent;
import me.minseoky.mcp_with_spring.dto.toolsList.components.ToolsListInputSchema;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class DummyToolRepository implements ToolRepository{

    private final List<Tool> tools = List.of(
        Tool.builder()
            .name("Awesome_Tool")
            .description("Get Greatest Mention")
            .inputSchema(
                ToolsListInputSchema.builder()
                    .type("object")
                    .properties(
                        Map.of()
                    )
                    .required(
                        List.of()
                    )
                    .build()
            )
            .build()
    );

    @Override
    public List<Tool> getAllTools() {
        return tools;
    }

    @Override
    public ToolsCallContent callTool(String toolName) {
        switch (toolName) {
            case "Awesome_Tool" -> {
                return ToolsCallContent.builder()
                    .type("text")
                    .text("바다는 비에 젖지 않는다.")
                    .build();
            }

            default -> {
                return null;
            }
        }
    }

}

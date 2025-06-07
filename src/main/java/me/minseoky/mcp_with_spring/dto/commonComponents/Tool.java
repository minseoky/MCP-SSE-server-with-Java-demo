package me.minseoky.mcp_with_spring.dto.commonComponents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.minseoky.mcp_with_spring.dto.toolsList.components.ToolsListInputSchema;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tool {

    private String name;

    private String description;

    private ToolsListInputSchema inputSchema;

}

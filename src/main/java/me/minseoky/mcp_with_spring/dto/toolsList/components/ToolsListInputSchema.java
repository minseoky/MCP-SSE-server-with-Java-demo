package me.minseoky.mcp_with_spring.dto.toolsList.components;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToolsListInputSchema {

    private String type;

    private Map<String, ToolsListProperty> properties; // key: parameter name

    private List<String> required; // required parameter names
}

package me.minseoky.mcp_with_spring.dto.toolsList.components;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToolsListProperty {

    private String type;

    private String description;

}

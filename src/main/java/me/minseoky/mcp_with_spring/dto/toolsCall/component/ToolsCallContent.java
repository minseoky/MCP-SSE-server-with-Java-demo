package me.minseoky.mcp_with_spring.dto.toolsCall.component;

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
public class ToolsCallContent {

    private String type;

    private String text;

}

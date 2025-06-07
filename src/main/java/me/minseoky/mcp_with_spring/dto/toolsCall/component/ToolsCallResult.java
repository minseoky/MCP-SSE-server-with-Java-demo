package me.minseoky.mcp_with_spring.dto.toolsCall.component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToolsCallResult {

    private List<ToolsCallContent> content;

}

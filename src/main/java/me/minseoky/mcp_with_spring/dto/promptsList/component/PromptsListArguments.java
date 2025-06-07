package me.minseoky.mcp_with_spring.dto.promptsList.component;

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
public class PromptsListArguments {
    private String name;
    private String description;
    private Boolean required;
}

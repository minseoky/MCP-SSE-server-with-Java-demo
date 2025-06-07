package me.minseoky.mcp_with_spring.dto.commonComponents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.minseoky.mcp_with_spring.dto.promptsList.component.PromptsListArguments;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prompt {

    private String name;

    private String description;

    private List<PromptsListArguments> arguments;

}
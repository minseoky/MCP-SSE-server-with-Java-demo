package me.minseoky.mcp_with_spring.dto.promptsList.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.minseoky.mcp_with_spring.dto.commonComponents.Prompt;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromptsListResult {

    @JsonProperty("prompts")
    private List<Prompt> prompts;

}

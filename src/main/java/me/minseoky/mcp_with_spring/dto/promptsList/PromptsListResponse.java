package me.minseoky.mcp_with_spring.dto.promptsList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.minseoky.mcp_with_spring.dto.commonComponents.Prompt;
import me.minseoky.mcp_with_spring.dto.promptsList.component.PromptsListResult;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromptsListResponse {

    @Builder.Default
    @JsonProperty("jsonrpc")
    private String jsonrpc = "2.0";

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("result")
    private PromptsListResult result;

    public static PromptsListResponse of(
        Integer id,
        List<Prompt> prompts
    ) {
        return PromptsListResponse.builder()
            .id(id)
            .result(
                PromptsListResult.builder()
                    .prompts(
                        prompts
                    )
                    .build()
            )
            .build();
    }

}

package me.minseoky.mcp_with_spring.dto.toolsCall;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.minseoky.mcp_with_spring.dto.promptsList.component.PromptsListResult;
import me.minseoky.mcp_with_spring.dto.toolsCall.component.ToolsCallContent;
import me.minseoky.mcp_with_spring.dto.toolsCall.component.ToolsCallResult;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ToolsCallResponse {

    @Builder.Default
    @JsonProperty("jsonrpc")
    private String jsonrpc = "2.0";

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("result")
    private ToolsCallResult result;


    public static ToolsCallResponse of(
        Integer id,
        ToolsCallContent content
    ) {
        return ToolsCallResponse.builder()
            .id(id)
            .result(
                ToolsCallResult.builder()
                    .content(
                        List.of(
                            ToolsCallContent.builder()
                                .type(content.getType())
                                .text(content.getText())
                                .build()
                        )
                    )
                    .build()
            )
            .build();
    }
}

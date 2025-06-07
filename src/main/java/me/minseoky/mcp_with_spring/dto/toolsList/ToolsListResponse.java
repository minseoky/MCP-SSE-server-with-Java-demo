package me.minseoky.mcp_with_spring.dto.toolsList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.minseoky.mcp_with_spring.dto.commonComponents.Tool;
import me.minseoky.mcp_with_spring.dto.toolsList.components.ToolsListResult;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ToolsListResponse {

    @Builder.Default
    @JsonProperty("jsonrpc")
    private String jsonrpc = "2.0";

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("result")
    private ToolsListResult result;

    public static ToolsListResponse of(
        Integer id,
        List<Tool> tools
    ) {
        return ToolsListResponse.builder()
            .id(id)
            .result(
                ToolsListResult.builder()
                    .tools(
                        tools
                    )
                    .build()
            )
            .build();
    }

}

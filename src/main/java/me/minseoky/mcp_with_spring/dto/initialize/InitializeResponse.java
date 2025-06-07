package me.minseoky.mcp_with_spring.dto.initialize;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.minseoky.mcp_with_spring.dto.initialize.component.InitializeCapabilities;
import me.minseoky.mcp_with_spring.dto.initialize.component.InitializePrompts;
import me.minseoky.mcp_with_spring.dto.initialize.component.InitializeResources;
import me.minseoky.mcp_with_spring.dto.initialize.component.InitializeResult;
import me.minseoky.mcp_with_spring.dto.initialize.component.InitializeServerInfo;
import me.minseoky.mcp_with_spring.dto.initialize.component.InitializeTools;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InitializeResponse {

    @Builder.Default
    @JsonProperty("jsonrpc")
    private String jsonrpc = "2.0";

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("result")
    private InitializeResult result;


    public static InitializeResponse of(
        Integer id,
        String mcpProtocolVersion,
        boolean promptsListChanged,
        boolean resourcesSubscribe,
        boolean resourcesListChanged,
        boolean toolsListChanged,
        String serverName,
        String serverVersion
    ) {
        return InitializeResponse.builder()
            .id(id)
            .result(
                InitializeResult.builder()
                    .protocolVersion(mcpProtocolVersion)
                    .capabilities(
                        InitializeCapabilities.builder()
                            .prompts(
                                InitializePrompts.builder()
                                    .listChanged(promptsListChanged)
                                    .build()
                            )
                            .resources(
                                InitializeResources.builder()
                                    .subscribe(resourcesSubscribe)
                                    .listChanged(resourcesListChanged)
                                    .build()
                            )
                            .tools(
                                InitializeTools.builder()
                                    .listChanged(toolsListChanged)
                                    .build()
                            )
                            .build()
                    )
                    .serverInfo(
                        InitializeServerInfo.builder()
                            .name(serverName)
                            .version(serverVersion)
                            .build()
                    )
                    .build()
            )
            .build();
    }
}
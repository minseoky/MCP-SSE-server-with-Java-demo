package me.minseoky.mcp_with_spring.dto.initialize.component;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class InitializeResult {

    @JsonProperty("protocolVersion")
    private String protocolVersion;

    @JsonProperty("capabilities")
    private InitializeCapabilities capabilities;

    @JsonProperty("serverInfo")
    private InitializeServerInfo serverInfo;

}
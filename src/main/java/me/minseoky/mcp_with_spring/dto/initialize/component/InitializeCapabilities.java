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
public class InitializeCapabilities {

    @JsonProperty("prompts")
    private InitializePrompts prompts;

    @JsonProperty("resources")
    private InitializeResources resources;

    @JsonProperty("tools")
    private InitializeTools tools;

}
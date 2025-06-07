package me.minseoky.mcp_with_spring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class McpRequest {

    @JsonProperty("jsonrpc")
    private String jsonrpc;

    @JsonProperty("id")
    private Integer id; // id가 없는 경우도 있으므로 Integer 사용

    @JsonProperty("method")
    private String method;

    @JsonProperty("params")
    private Map<String, Object> params; // 다양한 params를 포괄 처리

}
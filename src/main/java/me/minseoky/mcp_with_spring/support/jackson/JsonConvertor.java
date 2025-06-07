package me.minseoky.mcp_with_spring.support.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface JsonConvertor {

    String objectToJson(Object object) throws JsonProcessingException;

}

package me.minseoky.mcp_with_spring.repository;

import me.minseoky.mcp_with_spring.dto.commonComponents.Tool;
import me.minseoky.mcp_with_spring.dto.toolsCall.component.ToolsCallContent;

import java.util.List;

public interface ToolRepository {

    List<Tool> getAllTools();

    ToolsCallContent callTool(String toolName);

}

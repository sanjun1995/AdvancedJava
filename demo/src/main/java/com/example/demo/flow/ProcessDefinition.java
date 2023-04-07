package com.example.demo.flow;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caozhixin
 * @date 2023/4/6 21:48
 */
@Data
public class ProcessDefinition {
    private static List<FlowNode> flowNodes = new ArrayList<>();       // 流程定义中的节点列表
    private FlowNode startEvent;                                // 开始事件节点

    // 添加节点
    public void addFlowNode(FlowNode flowNode) {
        flowNodes.add(flowNode);
    }

    // 根据 ID 查找节点
    public FlowNode findFlowNodeById(String id) {
        for (FlowNode flowNode : flowNodes) {
            if (flowNode.getId().equals(id)) {
                return flowNode;
            }
        }
        return null;
    }

    // 设置开始事件节点
    public void setStartEvent(FlowNode startEvent) {
        this.startEvent = startEvent;
    }

    // 获取开始事件节点
    public FlowNode getStartEvent() {
        return startEvent;
    }

    public static List<FlowNode> getFlowNodes() {
        return flowNodes;
    }
}
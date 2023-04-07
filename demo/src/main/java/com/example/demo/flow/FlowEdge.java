package com.example.demo.flow;

import lombok.Data;

/**
 * @author caozhixin
 * @date 2023/4/6 21:47
 */
@Data
public class FlowEdge {
    protected String id;
    protected String name;
    protected String sourceNodeId;
    protected String targetNodeId;
    protected FlowNode sourceNode;
    protected FlowNode targetNode;

    // 连接线构造方法
    public FlowEdge(String id, String name, String sourceNodeId, String targetNodeId) {
        this.id = id;
        this.name = name;
        this.sourceNodeId = sourceNodeId;
        this.targetNodeId = targetNodeId;
    }
}
package com.example.demo.flow;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caozhixin
 * @date 2023/4/6 21:47
 */
@Data
// 流程节点抽象类
public abstract class FlowNode {
    protected String id;              // 节点 ID
    protected NodeType type;          // 节点类型
    protected String name;            // 节点名称
    protected String description;     // 节点描述

    public FlowNode(String id, NodeType type, String name, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
    }

    /** 执行节点，需要根据节点类型实现不同的业务逻辑 */
    public abstract void execute(ProcessInstance processInstance);

    /** 将任务移到下一个节点 */
    protected void moveToNextNode(ProcessInstance processInstance) {
        // 查询下一个节点
        FlowNode nextNode = processInstance.getDirectNextNode(this);
        // 如果下一个节点存在，则设置当前任务为完成，并设置下一个节点为当前节点
        if (nextNode != null) {
            Task currentTask = processInstance.getCurrentTask();
            currentTask.complete();
            processInstance.setCurrentNode(nextNode);
        }
    }

    public List<FlowNode> getOutgoingNodes() {
        List<FlowNode> outgoingNodes = new ArrayList<>();
        // 以顺序模式为例，假设节点按顺序连接，下一个节点就是列表中的下一个节点
        // 当然，实际流程定义可能更加复杂，需要采用其他方式查找后继节点
        int index = ProcessDefinition.getFlowNodes().indexOf(this);
        if (index >= 0 && index < ProcessDefinition.getFlowNodes().size() - 1) {
            outgoingNodes.add(ProcessDefinition.getFlowNodes().get(index + 1));
        }
        return outgoingNodes;
    }
}

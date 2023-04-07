package com.example.demo.flow;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

/**
 * @author caozhixin
 * @date 2023/4/6 21:49
 */
@Data
// 流程实例类，用于保存流程的执行状态和日志等信息
public class ProcessInstance {
    private String id;                  // 流程实例 ID
    private FlowNode currentNode;       // 当前节点
    private List<Task> tasks = new ArrayList<>();      // 已完成的任务列表

    public ProcessInstance(String id, FlowNode startEvent) {
        this.id = id;
        this.currentNode = startEvent;
    }

    // 添加并启动流程实例
    public static ProcessInstance create(ProcessDefinition processDefinition) {
        String id = UUID.randomUUID().toString();
        ProcessInstance instance = new ProcessInstance(id, processDefinition.getStartEvent());
        return instance;
    }

    // 获取直接后继节点
    public FlowNode getDirectNextNode(FlowNode currentNode) {
        // 检查当前节点是否为结束节点
        if (currentNode.getType() == NodeType.END_EVENT) {
            return null;
        }
        // XXX 这里需要根据具体实现获取下一个节点
        // 这里假设下一个节点就是流程定义中描述的下一个节点
        List<FlowNode> flowNodes = currentNode.getOutgoingNodes();
        if (flowNodes != null && flowNodes.size() > 0) {
            return flowNodes.get(0);
        } else {
            return null;
        }
    }

    // 添加任务
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    // 获取当前任务节点
    public Task getCurrentTask() {
        FlowNode currentNode = getCurrentNode();
        if (currentNode.getType() == NodeType.USER_TASK) {
            if (tasks.size() > 0) {
                return tasks.get(tasks.size() - 1);
            }
        } else if (currentNode.getType() == NodeType.SERVICE_TASK) {
            // XXX 这里需要根据具体实现创建新的任务
            // 这里假设下一个任务编号为前一任务编号加一
            int taskId = tasks.size() + 1;
            Task task = new Task("Task " + taskId, "Task " + taskId + " Description");
            addTask(task);
            return task;
        }
        return null;
    }

    // Task complete, 将任务标记为已完成
    public void completeTask(Task task) {
        ListIterator<Task> iterator = tasks.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(task)) {
                task.complete();
                return;
            }
        }
    }
}
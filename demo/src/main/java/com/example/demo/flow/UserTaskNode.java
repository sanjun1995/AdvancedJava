package com.example.demo.flow;

/**
 * @author caozhixin
 * @date 2023/4/6 22:18
 */
// 用户任务节点，用于等待用户完成任务
public class UserTaskNode extends FlowNode {
    public UserTaskNode(String id, String name, String description) {
        super(id, NodeType.USER_TASK, name, description);
    }

    @Override
    public void execute(ProcessInstance processInstance) {
        // 等待用户完成任务
        System.out.println("Waiting for user task " + getName() + " to be completed...");
    }
}

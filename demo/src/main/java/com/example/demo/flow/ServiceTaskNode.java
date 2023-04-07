package com.example.demo.flow;

/**
 * @author caozhixin
 * @date 2023/4/6 22:11
 */
// 服务任务节点，用于执行服务任务的具体逻辑
public class ServiceTaskNode extends FlowNode {
    private TaskHandler taskHandler;

    public ServiceTaskNode(String id, String name, String description, TaskHandler taskHandler) {
        super(id, NodeType.SERVICE_TASK, name, description);
        this.taskHandler = taskHandler;
    }

    @Override
    public void execute(ProcessInstance processInstance) {
        Task task = processInstance.getCurrentTask();
        // 执行服务任务处理器中的逻辑
        taskHandler.handleTask(task);
        // 将任务移到下一个节点
        moveToNextNode(processInstance);
    }
}

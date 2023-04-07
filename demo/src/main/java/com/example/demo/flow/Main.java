package com.example.demo.flow;

/**
 * @author caozhixin
 * @date 2023/4/6 22:21
 */
// 主函数入口
public class Main {
    public static void main(String[] args) {
        // 创建流程定义
        StartEventNode startEvent = new StartEventNode("start", "START", "开始");
        ServiceTaskNode serviceTask = new ServiceTaskNode("service", "SERVICE", "服务任务", new ExampleTaskHandler());
        UserTaskNode userTask = new UserTaskNode("user", "USER", "用户任务");
        EndEventNode endEvent = new EndEventNode("end", "END", "结束");

        // 启动流程实例
        ProcessDefinition processDefinition = new ProcessDefinition();
        processDefinition.addFlowNode(startEvent);
        processDefinition.addFlowNode(serviceTask);
        processDefinition.addFlowNode(userTask);
        processDefinition.addFlowNode(endEvent);

        ProcessInstance processInstance = ProcessInstance.create(processDefinition);
        // 执行流程
        while (processInstance.getCurrentNode() != null) {
            processInstance.getCurrentNode().execute(processInstance);
        }
    }
}

package com.example.demo.flow;

/**
 * @author caozhixin
 * @date 2023/4/6 21:51
 */
public class FlowEngine {
//    // BPMN2.0规范的XML Schema文件
//    private static final String BPMN_SCHEMA = "http://www.omg.org/spec/BPMN/20100524/MODEL";
//
//    // 流程定义
//    private ProcessDefinition processDefinition;
//
//    public FlowEngine(ProcessDefinition processDefinition) {
//        this.processDefinition = processDefinition;
//    }
//
//    // 启动流程
//    public void startProcess() {
//        ProcessInstance processInstance = new ProcessInstance();
//        processInstance.setProcessDefinition(processDefinition);
//        processInstance.start();
//    }
//
//    // 创建流程定义
//    public static ProcessDefinition createProcessDefinition() {
//        ProcessDefinition processDefinition = new ProcessDefinition();
//
//        // 添加流程节点
//        FlowNode startNode = new FlowNode("start", FlowNode.NodeType.START_EVENT, "Start Event", "Process Start Node");
//        processDefinition.addNode(startNode);
//
//        FlowNode serviceTaskNode = new ServiceTaskNode("service", "Service Task", "Process Service Node");
//        processDefinition.addNode(serviceTaskNode);
//
//        FlowNode endNode = new FlowNode("end", FlowNode.NodeType.END_EVENT, "End Event", "Process End Node");
//        processDefinition.addNode(endNode);
//
//        // 添加流程连线
//        FlowEdge edge1 = new FlowEdge("edge1", "Start to Service", "start", "service");
//        processDefinition.addEdge(edge1);
//
//        FlowEdge edge2 = new FlowEdge("edge2", "Service to End", "service", "end");
//        processDefinition.addEdge(edge2);
//
//        return processDefinition;
//    }
//
//    // 流程实例
//    public static class ExecutionContext {
//        private ProcessInstance processInstance;
//        private Map<String, Object> variables = new HashMap<>();
//
//        public ExecutionContext(ProcessInstance processInstance) {
//            this.processInstance = processInstance;
//        }
//
//        public Object getVariable(String variableName) {
//            return variables.get(variableName);
//        }
//
//        public void setVariable(String variableName, Object value) {
//            variables.put(variableName, value);
//        }
//
//        public ProcessInstance getProcessInstance() {
//            return processInstance;
//        }
//    }
//
//    // 服务任务流程节点
//    public static class ServiceTaskNode extends FlowNode {
//        private TaskHandler taskHandler;
//
//        public ServiceTaskNode(String id, String name, String description) {
//            super(id, NodeType.SERVICE_TASK, name, description);
//        }
//
//        @Override
//        public void execute(ExecutionContext ctx) {
//            Task task = new Task();
//            task.setName(getName());
//            task.setDescription(getDescription());
//
//            taskHandler.handleTask(task);
//
//            task.complete();
//        }
//
//        @Override
//        public boolean canReach(ExecutionContext ctx) {
//            return true;
//        }
//
//        public TaskHandler getTaskHandler() {
//            return taskHandler;
//        }
//
//        public void setTaskHandler(TaskHandler taskHandler) {
//            this.taskHandler = taskHandler;
//        }
//    }
}

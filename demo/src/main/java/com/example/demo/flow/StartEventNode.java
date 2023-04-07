package com.example.demo.flow;

/**
 * @author caozhixin
 * @date 2023/4/6 22:19
 */
// 开始事件节点，用于启动流程实例 
public class StartEventNode extends FlowNode {
    public StartEventNode(String id, String name, String description) {
        super(id, NodeType.START_EVENT, name, description);
    }

    @Override
    public void execute(ProcessInstance processInstance) {
        System.out.println("");
        moveToNextNode(processInstance);
    }
}

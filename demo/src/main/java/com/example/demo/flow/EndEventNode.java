package com.example.demo.flow;

/**
 * @author caozhixin
 * @date 2023/4/6 22:20
 */
// 结束事件节点，用于结束流程实例
public class EndEventNode extends FlowNode {
    public EndEventNode(String id, String name, String description) {
        super(id, NodeType.END_EVENT, name, description);
    }

    @Override
    public void execute(ProcessInstance processInstance) {
        System.out.println("Process instance " + processInstance.getId() + " has ended.");
    }
}

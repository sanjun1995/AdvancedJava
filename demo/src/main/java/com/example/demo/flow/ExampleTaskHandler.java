package com.example.demo.flow;

/**
 * @author caozhixin
 * @date 2023/4/6 22:20
 */
// 示例服务任务处理器实现类
public class ExampleTaskHandler implements TaskHandler {
    @Override
    public void handleTask(Task task) {
        System.out.println("Executing service task " + task.getName() + " (" + task.getDescription() + ")...");
        // XXX 这里添加具体的业务逻辑 }
    }
}

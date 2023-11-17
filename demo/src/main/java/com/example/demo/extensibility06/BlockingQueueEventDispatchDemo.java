package com.example.demo.extensibility06;

/**
 * @author caozhixin
 * @date 2023/11/17 22:24
 */
public class BlockingQueueEventDispatchDemo {
    public static void main(String[] args) {
        // 创建事件源
        EventSource02 eventSource = new EventSource02();

        // 注册监听器
        eventSource.addEventListener(new MyEventListener("Listener1"));
        eventSource.addEventListener(new MyEventListener("Listener2"));

        // 启动事件处理线程
        eventSource.startEventProcessing();

        // 触发事件
        Event event = new Event("BlockingQueueEvent");
        eventSource.fireEvent(event);

        Event event2 = new Event("BlockingQueueEvent2");
        eventSource.fireEvent(event2);

        // 关闭线程池
        eventSource.shutdown();
    }
}

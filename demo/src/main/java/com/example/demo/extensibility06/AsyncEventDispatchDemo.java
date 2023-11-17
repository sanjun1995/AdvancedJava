package com.example.demo.extensibility06;

/**
 * @author caozhixin
 * @date 2023/11/17 22:20
 */
public class AsyncEventDispatchDemo {
    public static void main(String[] args) {
        // 创建事件源
        EventSource eventSource = new EventSource();

        // 注册监听器
        eventSource.addEventListener(new MyEventListener("Listener1"));
        eventSource.addEventListener(new MyEventListener("Listener2"));

        // 触发事件
        Event event = new Event("AsyncEvent");
        eventSource.fireEvent(event);

        // 关闭线程池
        eventSource.shutdown();
    }
}

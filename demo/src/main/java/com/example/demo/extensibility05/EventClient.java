package com.example.demo.extensibility05;

/**
 * @author caozhixin
 * @date 2023/11/17 21:36
 */
public class EventClient {
    public static void main(String[] args) {
        // 创建事件源
        EventSource eventSourceForTest = new EventSourceForTest();
        EventSource eventSourceForTest2 = new EventSourceForTest2();

        // 创建事件监听器
        EventListener eventListener = new EventListenerForTest();
        EventListenerManager.addEventListener(eventListener);

        // 发布事件
        EventDispatcher.dispatchEvent(eventSourceForTest.fireEvent());
        EventDispatcher.dispatchEvent(eventSourceForTest2.fireEvent());
    }
}
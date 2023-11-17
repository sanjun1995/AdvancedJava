package com.example.demo.extensibility05;

/**
 * @author caozhixin
 * @date 2023/11/17 21:10
 */
public interface EventSource {
    /**
     * 发出事件
     *
     * @return
     */
    Event fireEvent();
}

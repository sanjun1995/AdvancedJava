package com.example.demo.extensibility05;

/**
 * @author caozhixin
 * @date 2023/11/17 21:34
 */
public interface EventListener {

    /**
     * 是否支持此事件
     *
     * @param event
     * @return
     */
    boolean supportEvent(Event event);

    /**
     * 处理事件
     *
     * @return
     */
    boolean handlerEvent(Event event);
}
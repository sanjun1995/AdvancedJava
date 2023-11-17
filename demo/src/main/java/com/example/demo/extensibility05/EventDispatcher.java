package com.example.demo.extensibility05;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

/**
 * @author caozhixin
 * @date 2023/11/17 21:35
 */
public class EventDispatcher {

    /**
     * 单例模式
     */
    private static EventDispatcher eventDispatcher = new EventDispatcher();

    private EventDispatcher() {

    }

    /**
     * 分发事件
     *
     * @param event
     * @return
     */
    public static boolean dispatchEvent(Event event) {
        if (CollectionUtils.isNotEmpty(EventListenerManager.getEventListenerList())) {
            for (EventListener eventListener : EventListenerManager.getEventListenerList()) {
                if (eventListener.supportEvent(event)) {
                    eventListener.handlerEvent(event);
                }
            }
        }
        return true;
    }
}

package com.example.demo.extensibility05;

/**
 * @author caozhixin
 * @date 2023/11/17 21:32
 */
public class EventSourceForTest2 implements EventSource {
    @Override
    public Event fireEvent() {

        Event event = new EventForTest2();
        System.out.println(getClass().getSimpleName() + " \t fireEvent " + event.getName());

        return event;
    }
}

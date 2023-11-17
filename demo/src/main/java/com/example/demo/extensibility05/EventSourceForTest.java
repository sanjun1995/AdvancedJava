package com.example.demo.extensibility05;

/**
 * @author caozhixin
 * @date 2023/11/17 21:10
 */
public class EventSourceForTest implements EventSource {
    @Override
    public Event fireEvent() {

        Event event = new EventForTest();
        System.out.println(getClass().getSimpleName() + " \t fireEvent " + event.getName());

        return event;
    }
}

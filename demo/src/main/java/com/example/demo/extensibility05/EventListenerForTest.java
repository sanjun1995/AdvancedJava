package com.example.demo.extensibility05;

/**
 * @author caozhixin
 * @date 2023/11/17 21:34
 */
public class EventListenerForTest implements EventListener {
    @Override
    public boolean supportEvent(Event event) {

        return event.getName().contains("Test");
    }

    @Override
    public boolean handlerEvent(Event event) {

        System.out.println(this.getClass().getSimpleName() + "\t handler " + event.getName());

        return true;
    }
}

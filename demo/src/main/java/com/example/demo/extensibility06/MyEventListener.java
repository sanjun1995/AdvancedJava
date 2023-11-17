package com.example.demo.extensibility06;

/**
 * @author caozhixin
 * @date 2023/11/17 22:19
 */
public class MyEventListener implements EventListener {
    private String name;

    public MyEventListener(String name) {
        this.name = name;
    }

    @Override
    public void handleEvent(Event event) {
        System.out.println("Listener " + name + " handles event: " + event.getName());
    }
}

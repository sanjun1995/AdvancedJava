package com.example.demo.extensibility05;

/**
 * @author caozhixin
 * @date 2023/11/17 21:33
 */
public class EventForTest implements Event {
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }
}

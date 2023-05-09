package com.example.demo.starter;

/**
 * @author caozhixin
 * @date 2023/5/8 19:46
 */
public class GreetingService {

    private String greeting;

    public GreetingService(String greeting) {
        this.greeting = greeting;
    }

    public String greet(String name) {
        return greeting + ", " + name + "!";
    }
}

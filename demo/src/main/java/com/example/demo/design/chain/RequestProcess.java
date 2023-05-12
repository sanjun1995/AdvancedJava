package com.example.demo.design.chain;

import org.springframework.stereotype.Component;

@Component
public class RequestProcess {
    @MyChain
    public void process(String data) {
        System.out.println(data);
    }
}

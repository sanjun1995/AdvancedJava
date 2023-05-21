package com.example.demo.design.chain;

import org.springframework.stereotype.Component;

@Component
public class BizComponent {
    @MyChain
    public void process(String data) {
        System.out.println(data);
    }
}

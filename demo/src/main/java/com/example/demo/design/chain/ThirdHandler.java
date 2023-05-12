package com.example.demo.design.chain;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class ThirdHandler implements ChainHandler {
    private ChainHandler nextHandler;

    @Override
    public void handle(Request request) {
        // 处理请求
        System.out.println("ThirdHandler handling request " + request);
    }

    @Override
    public void setNextHandler(ChainHandler handler) {
        this.nextHandler = handler;
    }
}

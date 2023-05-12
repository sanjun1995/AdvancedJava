package com.example.demo.design.chain;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class SecondHandler implements ChainHandler {
    private ChainHandler nextHandler;

    @Override
    public void handle(Request request) {
        // 处理请求
        System.out.println("SecondHandler handling request " + request);

        // 将请求传递给下一个处理器
        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }

    @Override
    public void setNextHandler(ChainHandler handler) {
        this.nextHandler = handler;
    }
}

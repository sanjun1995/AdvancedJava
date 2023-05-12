package com.example.demo.design.chain;

public interface ChainHandler {
    void handle(Request request);

    void setNextHandler(ChainHandler handler);
}

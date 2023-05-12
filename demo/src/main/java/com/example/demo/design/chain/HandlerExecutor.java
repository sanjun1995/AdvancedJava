package com.example.demo.design.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HandlerExecutor implements CommandLineRunner {

    @Autowired
    private List<ChainHandler> chainHandlers;

    /**
     * 构建处理器链
     */
    private ChainHandler buildHandlerChain() {
        ChainHandler headChainHandler = null;
        ChainHandler currentChainHandler = null;
        for (ChainHandler chainHandler : chainHandlers) {
            if (headChainHandler == null) {
                headChainHandler = chainHandler;
                currentChainHandler = headChainHandler;
            } else {
                currentChainHandler.setNextHandler(chainHandler);
                currentChainHandler = chainHandler;
            }
        }
        return headChainHandler;
    }

    @Override
    public void run(String... args) throws Exception {
        // 初始化处理器链
        ChainHandler headChainHandler = this.buildHandlerChain();
//        headHandlerChain.handle(request, response);
        Request request = new Request("Hello world!");
        headChainHandler.handle(request);
    }
}

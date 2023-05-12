package com.example.demo.design.chain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class HandlerExecutor implements CommandLineRunner {

    @Autowired
    private List<ChainHandler> chainHandlers;

    private ChainHandler chainHandler;

    @Autowired
    private RequestProcess process;

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
        // 构建责任链
        chainHandler = this.buildHandlerChain();
        // 执行某个方法（带Chain注解）
        process.process("Hello world!");
    }
}

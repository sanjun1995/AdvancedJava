package com.example.demo.b2c.fsmx.anno;

import org.springframework.stereotype.Component;

/**
 * @author caozhixin
 * @date 2024/9/20 10:37
 */
@Component
@FsmxProvider(module = "ModuleB", provideFor = ModuleProvider.ProvideFor.TX_MSG_PROVIDER)
class MyTxMsgProvider implements TransactionMsgProvider {
    @Override
    public void sendMsg() {
        System.out.println("TransactionMsgProvider: Sending transaction message...");
    }
}
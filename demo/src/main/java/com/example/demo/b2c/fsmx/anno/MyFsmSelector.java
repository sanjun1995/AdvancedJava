package com.example.demo.b2c.fsmx.anno;

import org.springframework.stereotype.Component;

/**
 * @author caozhixin
 * @date 2024/9/20 10:36
 */
@Component
@FsmxProvider(module = "ModuleA", provideFor = ModuleProvider.ProvideFor.FSM_SELECTOR)
class MyFsmSelector implements FsmSelector {
    @Override
    public void select() {
        System.out.println("FsmSelector: Selecting...");
    }
}

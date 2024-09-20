package com.example.demo.b2c.fsmx.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author caozhixin
 * @date 2024/9/20 10:32
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleProvider {
    String module();

    ProvideFor provideFor();

    enum ProvideFor {
        NONE(null),
        FSM_SELECTOR(FsmSelector.class),
        TX_MSG_PROVIDER(TransactionMsgProvider.class);

        private final Class<?> type;

        ProvideFor(Class<?> type) {
            this.type = type;
        }

        public Class<?> type() {
            return type;
        }
    }
}

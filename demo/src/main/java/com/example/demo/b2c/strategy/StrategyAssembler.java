package com.example.demo.b2c.strategy;

/**
 * @author caozhixin
 * @date 2024/9/13 09:36
 */
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ResolvableType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class StrategyAssembler<C, S extends Strategy<C>> implements ApplicationContextAware {

    private ApplicationContext context;
    private Map<C, S> strategyContainer = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
        initializeStrategies();
    }

    private void initializeStrategies() {
        ResolvableType resolvableType = ResolvableType.forClass(this.getClass());
        resolvableType = resolvableType.as(StrategyAssembler.class);
        resolvableType = resolvableType.getGeneric(1);
        Class<?> clazz = resolvableType.resolve();
        String[] beanNames = context.getBeanNamesForType(clazz);

        for (String beanName : beanNames) {
            @SuppressWarnings("unchecked")
            S strategy = (S) context.getBean(beanName);
            if (ignoreStrategy(strategy)) {
                continue;
            }

            Set<C> strategyCodes = strategy.strategyCodes();
            for (C code : strategyCodes) {
                strategyContainer.put(code, strategy);
            }
        }
    }

    public S strategyInstanceOf(C strategyCode) {
        return strategyContainer.get(strategyCode);
    }

    public boolean ignoreStrategy(S strategy) {
        if (strategy instanceof StrategyAssembler) {
            return true;
        }
        return false;
    }
}


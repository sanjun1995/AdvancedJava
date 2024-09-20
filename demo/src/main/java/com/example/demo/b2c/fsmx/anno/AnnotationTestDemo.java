package com.example.demo.b2c.fsmx.anno;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author caozhixin
 * @date 2024/9/20 10:38
 */
@SpringBootApplication
public class AnnotationTestDemo implements ApplicationContextAware, ApplicationListener<ApplicationReadyEvent> {
    static ApplicationContext applicationContext;
    public static void main(String[] args) {
        SpringApplication.run(AnnotationTestDemo.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AnnotationTestDemo.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Map<FsmxProvider, Object> providers = findProviders();

        // Access and use the providers
        for (Map.Entry<FsmxProvider, Object> entry : providers.entrySet()) {
            FsmxProvider anno = entry.getKey();
            System.out.println("Module: " + anno.module());

            if (anno.provideFor() == ModuleProvider.ProvideFor.FSM_SELECTOR) {
                FsmSelector selector = (FsmSelector) entry.getValue();
                selector.select();
            } else if (anno.provideFor() == ModuleProvider.ProvideFor.TX_MSG_PROVIDER) {
                TransactionMsgProvider provider = (TransactionMsgProvider) entry.getValue();
                provider.sendMsg();
            }
        }
    }

    private static Map<FsmxProvider, Object> findProviders() {
        String[] names = applicationContext.getBeanNamesForAnnotation(FsmxProvider.class);

        if (names == null || names.length == 0) {
            return Collections.emptyMap();
        }

        Map<FsmxProvider, Object> providers = new HashMap<>(names.length, 1.0f);

        for (String name : names) {
            Object provider = applicationContext.getBean(name);
            FsmxProvider anno = AnnotationUtils.findAnnotation(provider.getClass(), FsmxProvider.class);
            ModuleProvider.ProvideFor provideFor = anno.provideFor();
            Class<?> type = provideFor.type();
            Object object = type.cast(provider);
            providers.put(anno, object);
        }

        return providers;
    }
}

package com.example.demo.b2c.autoconfig;

/**
 * @author caozhixin
 * @date 2024/9/20 14:03
 */
public class GenericFsmActionAdaptorBeanRegistrar {
    private final String[] scanBasePackages;

    public GenericFsmActionAdaptorBeanRegistrar(String[] scanBasePackages) {
        this.scanBasePackages = scanBasePackages;
        System.out.println("Registering actions from: " + String.join(", ", scanBasePackages));
    }
}

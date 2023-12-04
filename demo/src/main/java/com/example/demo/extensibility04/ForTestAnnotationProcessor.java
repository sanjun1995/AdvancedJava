package com.example.demo.extensibility04;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * @author caozhixin
 * @date 2023/11/17 20:42
 */
@Component
public class ForTestAnnotationProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        // 获取目标类是否有ForTestAnnotation注解
        ForTestAnnotation annotation = AnnotationUtils.findAnnotation(AopUtils.getTargetClass(bean), ForTestAnnotation.class);

        if (annotation == null) {
            return bean;
        }

        // 处理想要的扩展
        System.out.println(beanName + " has ForTestAnnotation");

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("after beanName: " + beanName);
        return bean;
    }
}

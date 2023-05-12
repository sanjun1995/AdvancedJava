package com.example.demo.design.chain;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ChainHandlerAspect {
    @Autowired
    private HandlerExecutor executor;

    @Around("@annotation(com.example.demo.design.chain.MyChain)")
    public Object checkParam(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs(); // 获取方法的所有参数
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Class<?>[] parameterTypes = method.getParameterTypes(); // 获取方法的参数类型列表

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].getName().equals("java.lang.String")) {
                executor.getChainHandler().handle(new Request((String) args[0]));
            }
        }
        return pjp.proceed();
    }
}


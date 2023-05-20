package com.example.demo.design.chain;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
public class ChainHandlerAspect implements InitializingBean {
    @Autowired
    // 注入所有实现ChainHandler接口的责任链处理器
    private List<ChainHandler> chainHandlers;

    // 责任链的头节点
    private ChainHandler chainHandler;

    @Around("@annotation(com.example.demo.design.chain.MyChain)")
    public Object checkParam(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs(); // 获取方法的所有参数
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Class<?>[] parameterTypes = method.getParameterTypes(); // 获取方法的参数类型列表

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].getName().equals("java.lang.String")) {
                chainHandler.handle(new Request((String) args[0]));
            }
        }
        return pjp.proceed();
    }

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
    public void afterPropertiesSet() throws Exception {
        // 构建责任链
        chainHandler = this.buildHandlerChain();
    }
}


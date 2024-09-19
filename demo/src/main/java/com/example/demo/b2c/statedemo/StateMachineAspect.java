package com.example.demo.b2c.statedemo;

/**
 * @author caozhixin
 * @date 2024/9/19 10:48
 */
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class StateMachineAspect {

    @Around("@annotation(Transactional)")
    public Object aroundTransactional(ProceedingJoinPoint joinPoint) throws Throwable {
        // 开始事务
        System.out.println("Starting transaction...");
        try {
            Object result = joinPoint.proceed();
            // 提交事务
            System.out.println("Committing transaction...");
            return result;
        } catch (Throwable throwable) {
            // 回滚事务
            System.out.println("Rolling back transaction...");
            throw throwable;
        }
    }

    @Around("@annotation(retry)")
    public Object aroundRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        int maxRetries = retry.value();
        for (int attempt = 0; attempt < maxRetries; attempt++) {
            try {
                return joinPoint.proceed(); // 尝试执行
            } catch (Exception e) {
                System.out.println("Attempt " + (attempt + 1) + " failed, retrying...");
                if (attempt == maxRetries - 1) {
                    throw e; // 达到最大重试次数，抛出异常
                }
            }
        }
        return null; // 这行不会到达
    }
}

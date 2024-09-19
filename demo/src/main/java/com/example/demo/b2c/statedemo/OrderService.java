package com.example.demo.b2c.statedemo;

/**
 * @author caozhixin
 * @date 2024/9/19 10:49
 */
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final SimpleStateMachine stateMachine;

    public OrderService() {
        this.stateMachine = new SimpleStateMachine("NEW");
        stateMachine.addTransition("NEW", "PROCESSING");
        stateMachine.addTransition("PROCESSING", "COMPLETED");
    }

    @Transactional
    @Retry
    public void processOrder(String event) {
        // 处理订单逻辑
        stateMachine.transition(event);
        System.out.println("Order processed. Current state: " + stateMachine.getCurrentState());
        throw new RuntimeException("test：" + event);
    }
}

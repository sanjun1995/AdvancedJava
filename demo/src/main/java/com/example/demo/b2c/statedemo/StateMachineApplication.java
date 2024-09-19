package com.example.demo.b2c.statedemo;

/**
 * @author caozhixin
 * @date 2024/9/19 10:49
 */
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StateMachineApplication implements CommandLineRunner {

    private final OrderService orderService;

    public StateMachineApplication(OrderService orderService) {
        this.orderService = orderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(StateMachineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        orderService.processOrder("EVENT_PROCESS");
        orderService.processOrder("EVENT_COMPLETE");
    }
}

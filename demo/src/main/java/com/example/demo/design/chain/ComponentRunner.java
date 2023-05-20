package com.example.demo.design.chain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Data
public class ComponentRunner implements CommandLineRunner {

    @Autowired
    private RequestProcess process;

    @Override
    public void run(String... args) throws Exception {
        // 执行某个方法（带Chain注解）
        process.process("Hello world!");
    }
}

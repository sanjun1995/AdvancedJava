package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caozhixin
 * @date 2023/4/7 21:12
 */
@RestController
public class HelloController2 {

    @GetMapping("/hello2")
    public String hello() {
        return "Hello2 World!";
    }
}

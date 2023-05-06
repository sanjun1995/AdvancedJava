package com.example.demo.agent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author caozhixin
 * @date 2023/5/6 14:43
 */
@Slf4j
public class Demo01 {
    public static void check() {
        try {
            System.out.println("Testing Demo");
        } catch (Exception e) {
            log.error("Error={}", e);
        }
    }
}

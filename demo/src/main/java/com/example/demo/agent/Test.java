package com.example.demo.agent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author caozhixin
 * @date 2023/5/6 14:31
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            // 由字节码增强来输出
        }
    }
}

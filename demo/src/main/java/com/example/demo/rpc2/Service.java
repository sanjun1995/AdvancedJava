package com.example.demo.rpc2;

/**
 * @author caozhixin
 * @date 2023/3/31 21:46
 */
public interface Service {
    Response callMethod(String methodName, String[] args);
}

package com.example.demo.rpc;

/**
 * @author caozhixin
 * @date 2023/3/30 23:55
 */
public class HelloServiceImpl implements HelloService {
    // 实现服务接口方法
    @Override
    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }
}

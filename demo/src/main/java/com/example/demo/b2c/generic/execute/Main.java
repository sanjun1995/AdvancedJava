package com.example.demo.b2c.generic.execute;

/**
 * @author caozhixin
 * @date 2024/9/14 11:16
 */
public class Main {
    public static void main(String[] args) {
        // 成功执行示例
        Result<String> successResult = ExecuteUtil.execute(() -> {
            // 模拟成功
            return "执行成功!";
        }, e -> System.out.println("日志记录: " + e.getMessage()));

        System.out.println(successResult);

        // 异常捕获示例
        Result<String> errorResult = ExecuteUtil.execute(() -> {
            // 模拟异常
            throw new RuntimeException("模拟运行时异常");
        }, e -> System.out.println("错误日志: " + e.getMessage()), "自定义错误信息");

        System.out.println(errorResult);

        // 空指针异常示例
        Result<String> npeResult = ExecuteUtil.execute(() -> {
            // 模拟空指针异常
            throw new NullPointerException();
        }, e -> System.out.println("空指针异常日志: " + e.getMessage()));

        System.out.println(npeResult);
    }
}

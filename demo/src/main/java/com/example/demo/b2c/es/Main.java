package com.example.demo.b2c.es;

import java.util.function.Function;

/**
 * @author caozhixin
 * @date 2024/9/13 14:11
 */
public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        UserQueryCondition condition = new UserQueryCondition(10, 1); // 页大小为 10，页码为 1

        // 定义 Function 将 JSON 字符串转换为 User 对象
        Function<String, User> function = json -> {
            String[] parts = json.replaceAll("[{}\"]", "").split(", ");
            Long id = Long.parseLong(parts[0].split(": ")[1]);
            String name = parts[1].split(": ")[1];
            return new User(id, name);
        };

        // 执行 ID 查询
        Page<Long> idPage = userService.search(condition);
        System.out.println("ID 查询结果: " + idPage);

        // 执行实体查询
        Page<User> userPage = userService.search(condition, function);
        System.out.println("实体查询结果: " + userPage);
    }
}

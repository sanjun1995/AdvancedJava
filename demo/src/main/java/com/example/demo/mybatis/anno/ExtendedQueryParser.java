package com.example.demo.mybatis.anno;

import java.lang.reflect.Method;

public class ExtendedQueryParser {
    public static void parse(Method method, Object[] args) {
        if (method.isAnnotationPresent(ExtendedQuery.class)) {
            ExtendedQuery extendedQuery = method.getAnnotation(ExtendedQuery.class);
            String queryType = extendedQuery.value();

            if (queryType.equals("fuzzy")) {
                // 执行模糊查询逻辑
                String keyword = args[0].toString();
                args[0] = "%" + keyword + "%";
            } else if (queryType.equals("orderByDesc")) {
                // 执行倒序排序逻辑
                // 无需修改参数
            }
        }
    }
}
package com.example.demo.b2c.es;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author caozhixin
 * @date 2024/9/13 14:10
 */
public class UserService extends AbstractEsSupplierService<UserQueryCondition, Long> {
    @Override
    public Page<Long> search(UserQueryCondition queryCondition) {
        // 这里可以根据条件返回 ID 列表，简单模拟返回 1 到 3 的 ID
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        return new Page<>(ids, queryCondition.getPageNumber(), queryCondition.getPageSize());
    }

    @Override
    public <R> Page<R> search(UserQueryCondition queryCondition, Function<String, R> function) {
        // 模拟从 Elasticsearch 查询的 JSON 字符串结果
        List<String> jsonHits = Arrays.asList(
                "{\"id\": 1, \"name\": \"Alice\"}",
                "{\"id\": 2, \"name\": \"Bob\"}",
                "{\"id\": 3, \"name\": \"Charlie\"}"
        );

        // 使用 Function 处理查询结果
        List<R> results = jsonHits.stream()
                .map(function)
                .collect(Collectors.toList());

        return new Page<>(results, queryCondition.getPageNumber(), queryCondition.getPageSize());
    }
}

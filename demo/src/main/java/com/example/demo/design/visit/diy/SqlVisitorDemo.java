package com.example.demo.design.visit.diy;

/**
 * @author caozhixin
 * @date 2024/11/18 17:01
 */
public class SqlVisitorDemo {
    public static void main(String[] args) {
        // 构建一个简单的 SQL 查询
        SqlNode sql = new SqlSelect(
                new SqlIdentifier("user_table"),
                new SqlLiteral("age > 25")
        );

        // 使用 SqlPrintVisitor 来打印 SQL 查询
        SqlPrintVisitor printVisitor = new SqlPrintVisitor();
        String result = sql.accept(printVisitor);
        System.out.println(result);  // 输出：SELECT FROM user_table WHERE 'age > 25'

        // 使用 SqlFunctionExtractor 提取函数
        SqlFunctionExtractor functionExtractor = new SqlFunctionExtractor();
        functionExtractor.extract(sql);  // 执行提取操作
    }
}


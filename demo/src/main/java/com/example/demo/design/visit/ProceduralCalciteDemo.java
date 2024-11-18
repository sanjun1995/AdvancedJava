package com.example.demo.design.visit;

import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author caozhixin
 * @date 2024/11/18 16:40
 */
public class ProceduralCalciteDemo {

    public static void main(String[] args) throws SqlParseException {
        // 示例 SQL
        String sql = "SELECT CONCAT('test-', UPPER(name)), id FROM user_table WHERE age > 25";

        // 解析 SQL
        SqlParser parser = SqlParser.create(sql);
        SqlNode sqlNode = parser.parseStmt();

        // 逐层遍历 AST，提取信息
        List<String> functions = new ArrayList<>();
        Set<String> tableNames = new HashSet<>();
        List<String> columnNames = new ArrayList<>();
        List<String> literals = new ArrayList<>();

        // 模拟遍历树的逻辑
        extractInfo(sqlNode, functions, tableNames, columnNames, literals, false);

        // 打印结果
        System.out.println("Functions: " + functions);
        System.out.println("Table Names: " + tableNames);
        System.out.println("Column Names: " + columnNames);
        System.out.println("Literals: " + literals);
    }

    public static void extractInfo(SqlNode node, List<String> functions, Set<String> tableNames,
                                   List<String> columnNames, List<String> literals, boolean inFromClause) {
        if (node instanceof SqlCall) {
            SqlCall call = (SqlCall) node;
            SqlOperator operator = call.getOperator();
            if (operator != null && operator.getName() != null) {
                functions.add(operator.getName());
            }
            // 如果是 SELECT，进入 FROM 子句
            if (call.getKind() == SqlKind.SELECT) {
                SqlSelect select = (SqlSelect) call;
                if (select.getFrom() != null) {
                    extractInfo(select.getFrom(), functions, tableNames, columnNames, literals, true);
                }
                if (select.getWhere() != null) {
                    extractInfo(select.getWhere(), functions, tableNames, columnNames, literals, false);
                }
                if (select.getSelectList() != null) {
                    for (SqlNode item : select.getSelectList()) {
                        extractInfo(item, functions, tableNames, columnNames, literals, false);
                    }
                }
            } else {
                // 递归处理子节点
                for (SqlNode operand : call.getOperandList()) {
                    extractInfo(operand, functions, tableNames, columnNames, literals, inFromClause);
                }
            }
        } else if (node instanceof SqlIdentifier) {
            SqlIdentifier id = (SqlIdentifier) node;
            if (inFromClause) {
                tableNames.add(id.toString());
            } else {
                columnNames.add(id.toString());
            }
        } else if (node instanceof SqlLiteral) {
            SqlLiteral literal = (SqlLiteral) node;
            literals.add(literal.toString());
        }
    }
}

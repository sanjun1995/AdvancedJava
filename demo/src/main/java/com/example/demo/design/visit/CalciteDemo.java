package com.example.demo.design.visit;

import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.util.SqlBasicVisitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CalciteDemo {

    public static void main(String[] args) throws SqlParseException {
        // 示例 SQL
        String sql = "SELECT CONCAT('test-', UPPER(name)), id FROM user_table WHERE age > 25";

        // 解析 SQL
        SqlParser parser = SqlParser.create(sql);
        SqlNode sqlNode = parser.parseStmt();

        // 使用访问者模式提取信息
        SqlInfoExtractor extractor = new SqlInfoExtractor();
        sqlNode.accept(extractor);

        // 打印提取的信息
        System.out.println("Functions: " + extractor.getFunctions());
        System.out.println("Table Names: " + extractor.getTableNames());
        System.out.println("Column Names: " + extractor.getColumnNames());
        System.out.println("Literals: " + extractor.getLiterals());
    }

    // 自定义访问者类
    static class SqlInfoExtractor extends SqlBasicVisitor<Void> {
        private final List<String> functions = new ArrayList<>();
        private final Set<String> tableNames = new HashSet<>();
        private final List<String> columnNames = new ArrayList<>();
        private final List<String> literals = new ArrayList<>();
        private boolean inFromClause = false;

        // 提取函数名
        @Override
        public Void visit(SqlCall call) {
            SqlOperator operator = call.getOperator();
            if (operator != null && operator.getName() != null) {
                functions.add(operator.getName());
            }

            // 检查是否进入 FROM 子句
            if (call.getKind() == SqlKind.SELECT) {
                SqlSelect select = (SqlSelect) call;
                if (select.getFrom() != null) {
                    inFromClause = true;
                    select.getFrom().accept(this);
                    inFromClause = false;
                }
            }
            return super.visit(call);
        }

        // 提取表名和列名
        @Override
        public Void visit(SqlIdentifier id) {
            if (inFromClause) {
                // 如果在 FROM 子句中，则将当前标识符视为表名
                tableNames.add(id.toString());
            } else if (!tableNames.contains(id.toString())) {
                // 如果不在表名集合中，则视为列名
                columnNames.add(id.toString());
            }
            return super.visit(id);
        }

        // 提取常量
        @Override
        public Void visit(SqlLiteral literal) {
            literals.add(literal.toString());
            return super.visit(literal);
        }

        // Getter 方法
        public List<String> getFunctions() {
            return functions;
        }

        public List<String> getTableNames() {
            return new ArrayList<>(tableNames);
        }

        public List<String> getColumnNames() {
            return columnNames;
        }

        public List<String> getLiterals() {
            return literals;
        }
    }
}

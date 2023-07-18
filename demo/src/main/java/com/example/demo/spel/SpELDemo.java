package com.example.demo.spel;

/**
 * @author caozhixin
 * @date 2023/7/18 15:14
 */
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELDemo {
    public static void main(String[] args) {
        // 创建SpEL表达式解析器
        SpelExpressionParser parser = new SpelExpressionParser();

        // 创建评估上下文
        StandardEvaluationContext context = new StandardEvaluationContext();

        // 在评估上下文中设置变量
        Result result = new Result("SUCCESS"); // 假设Result是一个具有returnCode属性的类
        context.setVariable("result", result);

        // 解析SpEL表达式
        Expression expression = parser.parseExpression("#result.returnCode == 'SUCCESS'");

        // 评估表达式
        boolean evaluationResult = expression.getValue(context, Boolean.class);

        // 输出结果
        System.out.println("Evaluation result: " + evaluationResult);
    }
}

class Result {
    private String returnCode;

    public Result(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnCode() {
        return returnCode;
    }
}


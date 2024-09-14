package com.example.demo.b2c.generic.execute;

import java.util.function.Consumer;

/**
 * @author caozhixin
 * @date 2024/9/14 11:15
 */
public class ExecuteUtil {
    private static final String DEFAULT_ERROR_MSG = "系统异常";

    public static <T> Result<T> execute(TryCatchRetFunction<T> function, Consumer<Exception> logConsumer, String errorMsg) {
        try {
            return Result.success(function.apply());
        } catch (NullPointerException nullPointerExc) {
            logConsumer.accept(nullPointerExc);
            return Result.error(-1, DEFAULT_ERROR_MSG + ": 空指针异常");
        } catch (Exception e) {
            logConsumer.accept(e);
            return Result.error(-1, errorMsg != null ? errorMsg : DEFAULT_ERROR_MSG);
        }
    }

    public static <T> Result<T> execute(TryCatchRetFunction<T> function, Consumer<Exception> logConsumer) {
        return execute(function, logConsumer, null);
    }
}

package com.example.demo.b2c.generic.execute;

/**
 * @author caozhixin
 * @date 2024/9/14 11:13
 */
public class Result<T> {
    private int code;
    private String message;
    private T data;

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(0, "操作成功", data);
    }

    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message, null);
    }

    @Override
    public String toString() {
        return "Result{code=" + code + ", message='" + message + "', data=" + data + '}';
    }
}

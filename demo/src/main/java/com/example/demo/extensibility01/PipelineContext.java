package com.example.demo.extensibility01;

/**
 * @author caozhixin
 * @date 2023/11/17 15:13
 */
public interface PipelineContext {

    String FOR_TEST = "forTest";

    /**
     * 设置
     *
     * @param contextKey
     * @param contextValue
     */
    void set(String contextKey, Object contextValue);

    /**
     * 获取值
     *
     * @param contextKey
     * @return
     */
    Object get(String contextKey);
}

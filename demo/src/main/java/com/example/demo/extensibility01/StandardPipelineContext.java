package com.example.demo.extensibility01;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author caozhixin
 * @date 2023/11/17 15:17
 */
public class StandardPipelineContext implements PipelineContext {

    private Map<String, Object> contentMap = Maps.newConcurrentMap();

    @Override
    public void set(String contextKey, Object contextValue) {
        contentMap.put(contextKey, contextValue);
    }

    @Override
    public Object get(String contextKey) {
        return contentMap.get(contextKey);
    }
}

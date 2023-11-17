package com.example.demo.extensibility01;

/**
 * @author caozhixin
 * @date 2023/11/17 15:13
 */
public interface PipelineValue {
    /**
     * 节点执行
     *
     * @param pipelineContext
     * @return
     */
    boolean execute(PipelineContext pipelineContext);
}

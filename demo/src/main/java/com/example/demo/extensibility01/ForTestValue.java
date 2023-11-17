package com.example.demo.extensibility01;

/**
 * @author caozhixin
 * @date 2023/11/17 15:22
 */
public class ForTestValue extends AbstractPipelineValue {
    @Override
    public boolean doExec(PipelineContext pipelineContext) {
        return false;
    }
}

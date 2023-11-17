package com.example.demo.extensibility01;

/**
 * @author caozhixin
 * @date 2023/11/17 15:22
 */
public class GraySwitchValue extends AbstractPipelineValue {
    @Override
    public boolean doExec(PipelineContext pipelineContext) {

        pipelineContext.set(PipelineContext.FOR_TEST, true);

        return true;
    }
}

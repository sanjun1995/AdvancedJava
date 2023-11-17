package com.example.demo.extensibility01;

/**
 * @author caozhixin
 * @date 2023/11/17 15:19
 */
public abstract class AbstractPipelineValue implements PipelineValue {

    @Override
    public boolean execute(PipelineContext pipelineContext) {

        System.out.println(this.getClass().getSimpleName() + " start ");

        boolean result = doExec(pipelineContext);

        System.out.println(this.getClass().getSimpleName() + " end ");

        return result;
    }

    protected abstract boolean doExec(PipelineContext pipelineContext);
}

package com.example.demo.extensibility01;

/**
 * @author caozhixin
 * @date 2023/11/17 15:23
 */
public class PipelineClient {

    public static void main(String[] args) {

        // 管道初始化
        Pipeline pipeline = new StandardPipeline();

        // value扩展
        PipelineValue pipelineValue = new GraySwitchValue();
        PipelineValue pipelineValue2 = new ForTestValue();

        pipeline.addValue(pipelineValue);
        pipeline.addValue(pipelineValue2);

        // 上下文
        PipelineContext pipelineContext = new StandardPipelineContext();

        // 调用管道
        pipeline.invoke(pipelineContext);
    }
}

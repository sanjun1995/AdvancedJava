package com.example.demo.b2c.fsmx.nestanno;

/**
 * @author caozhixin
 * @date 2024/9/20 11:24
 */
@Action
public class Service {
    @ActionBefore(
            value = {
                    ActionBefore.Operate.OPERATE_1,
                    ActionBefore.Operate.OPERATE_2
            },
            resultAsParam = @ActionBefore.ResultAsParam(asChainParam = true)
    )
    public void test1() {
        System.out.println("test1。");
    }

    @ActionBefore(
            value = {ActionBefore.Operate.OPERATE_2},
            order = 1
    )
    public void test2() {
        System.out.println("test2。");
    }
}

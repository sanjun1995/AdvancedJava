package com.example.demo.b2c.fsmx.anno2;

/**
 * @author caozhixin
 * @date 2024/9/20 17:07
 */
public class Main {

    @SonDoAction({
            Operate.O_1,
            Operate.O_2,
            Operate.O_3,
            Operate.O_4
    })
    @DoAction
    public void updateWarehouseInfo() {
        // Implementation
    }

    public static void main(String[] args) {
        AnnotationProcessor processor = new AnnotationProcessor();
        processor.initGenericFsmActionBean(Main.class, new Main());
    }
}
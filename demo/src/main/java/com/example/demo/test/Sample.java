package com.example.demo.test;

/**
 * @author caozhixin
 * @date 2022/9/22 3:25 PM
 */
public class Sample {
    public static void main(String[] args){
        //得到类的简写名称
        System.out.println(Sample.class.getSimpleName());

        //得到对象的全路径
        System.out.println(Sample.class);

        //得到对象的类模板示例，也就是Class
        System.out.println(Sample.class.getClass());

        //得到Class类的名称
        System.out.println(Sample.class.getClass().getName());
    }
}

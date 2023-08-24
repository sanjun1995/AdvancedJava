package com.example.demo.algo.zhuan;

/**
 * @author caozhixin
 * @date 2023/8/7 16:29
 */
public class ValueDemo {
//    public static void main(String[] args) {
//        Integer a = 10;
//        Integer b = 12;
//        transform(a, b);
//        System.out.println("a: " + a);
//        System.out.println("b: " + b);
//
//        Dog dog1 = new Dog(10);
//        Dog dog2 = new Dog(12);
//        transform2(dog1, dog2);
//        System.out.println("dog1: " + dog1.getAge());
//        System.out.println("dog2: " + dog2.getAge());
//    }

    public static void main(String[] args) {
        int a = 10;
        int b = 12;
        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }

    public static void transform(int a, int b) {
        a = a * 2;
        b = b * 2;
    }

    public static void transform2(Dog dog1, Dog dog2) {
        dog1.setAge(dog1.getAge() * 2);
        dog2.setAge(dog2.getAge() * 2);
    }
}

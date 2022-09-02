package com.example.demo.bit;

/**
 * @author caozhixin
 * @date 2022/9/2 10:21 PM
 */
public class BitDemo {
    public static void main(String[] args) {
        // 奇偶判断
        System.out.println((24 & 1) == 0);
        System.out.println("--------------------------------------");
        // 清零最后一位
        System.out.println(Integer.toBinaryString(7));
        System.out.println(Integer.toBinaryString(7 & (7 - 1)));
        System.out.println("--------------------------------------");
        // 得到最低位的1
        System.out.println(Integer.toBinaryString(9));
        System.out.println(Integer.toBinaryString(-9));
        System.out.println(Integer.toBinaryString(9 & -9));
    }
}

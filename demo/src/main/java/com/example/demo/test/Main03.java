package com.example.demo.test;

/**
 * @author caozhixin
 * @date 2023/12/12 17:11
 */
public class Main03 {
    public void t0() {
        Long v = false ? 1L : t12();
        System.out.println(v);
    }

    public void t1() {
        Long v = false ? t11() : t12();
        System.out.println(v);
    }

    public Long t11() {
        return 1L;
    }

    public Long t12() {
        return null;
    }

    public static void main(String[] args) {
        Main03 main03 = new Main03();
        main03.t0();
        main03.t1();
    }
}

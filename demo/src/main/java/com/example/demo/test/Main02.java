package com.example.demo.test;

/**
 * @author caozhixin
 * @date 2023/12/11 17:42
 */
class ListNodev {
    int val;
    ListNodev next;
    ListNodev(int x) {
        val = x;
        next = null;
    }
}

public class Main02 {
    public static void main(String[] args) {
        // 创建节点
        ListNodev node1 = new ListNodev(3);
        ListNodev node2 = new ListNodev(2);
        ListNodev node3 = new ListNodev(0);
        ListNodev node4 = new ListNodev(-4);

        // 构建环形链表
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // -4 指向 2，形成环

        // 输出环的入口节点的值
        System.out.printf("listNode = %s%n", node1);
    }
}

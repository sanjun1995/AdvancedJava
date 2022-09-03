package com.example.demo.algo;

import lombok.Data;

import java.util.Stack;

/**
 * 链表反转
 * @author caozhixin
 * @date 2022/9/2 10:56 PM
 */
public class ListNodeDemo {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
////        System.out.println(reverseList(listNode1));
//        System.out.println(swapPairs(listNode1));
    }

//    public static ListNode swapPairs(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode next = head.next;
//        head.next = swapPairs(next.next);
//        next.next = head;
//        return next;
//    }

//    public static ListNode swapPairs(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode p = new ListNode(-1);
//        ListNode curr = head;
//        head = p;
//        Stack<ListNode> stack = new Stack<>();
//        while (curr != null && curr.next != null) {
//            stack.add(curr);
//            stack.add(curr.next);
//            curr = curr.next.next;
//            p.next = stack.pop();
//            p = p.next;
//            p.next = stack.pop();
//            p = p.next;
//        }
//        p.next = curr;
//        return head.next;
//    }

//    public static ListNode reverseList(ListNode head) {
//        ListNode curr = head;
//        ListNode prev = null;
//        while (curr != null) {
//            // 先存储后一个节点
//            ListNode next = curr.next;
//            // 当前节点引用前一个节点
//            curr.next = prev;
//            // 两个节点同时向前移动
//            prev = curr;
//            curr = next;
//        }
//        return prev;
//    }

    @Data
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

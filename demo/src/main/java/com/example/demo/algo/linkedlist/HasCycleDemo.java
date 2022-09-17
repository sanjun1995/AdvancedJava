package com.example.demo.algo.linkedlist;


import java.util.HashSet;
import java.util.Set;

/**
 * @author caozhixin
 * @date 2022/9/17 2:53 PM
 */
public class HasCycleDemo {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(0);
        ListNode n3 = new ListNode(4);
        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;
        System.out.println(hasCycle(head));
    }
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

//    public static boolean hasCycle(ListNode head) {
//        if (head == null || head.next == null) {
//            return false;
//        }
//        ListNode slow = head;
//        ListNode fast = head.next;
//        while (slow != fast) {
//            if (fast == null || fast.next == null) {
//                return false;
//            }
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//        return true;
//    }

//    public static boolean hasCycle(ListNode head) {
//        Set<ListNode> seen = new HashSet<>();
//        while (head != null) {
//            if (!seen.add(head)) {
//                return true;
//            }
//            head = head.next;
//        }
//        return false;
//    }
}

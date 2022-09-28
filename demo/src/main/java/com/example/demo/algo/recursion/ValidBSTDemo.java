package com.example.demo.algo.recursion;

import com.example.demo.algo.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author caozhixin
 * @date 2022/9/25 9:10 PM
 */
public class ValidBSTDemo {
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个inorde, 说明不是二叉搜索树
            // 因为二叉搜索树的中序遍历是有序递增的数列
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }
//    public boolean isValidBST(TreeNode root) {
//        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
//    }
//
//    private boolean isValidBST(TreeNode node, long lower, long upper) {
//        if (node == null) {
//            return true;
//        }
//        if (node.val <= lower || node.val >= upper) {
//            return false;
//        }
//        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
//    }
}

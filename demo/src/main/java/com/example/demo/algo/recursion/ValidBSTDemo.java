package com.example.demo.algo.recursion;

import com.example.demo.algo.TreeNode;

/**
 * @author caozhixin
 * @date 2022/9/25 9:10 PM
 */
public class ValidBSTDemo {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }
}

package lusibian.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class _98ValidateBinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST(TreeNode root) {
        List<Integer> inorderList = new ArrayList<>();
        return inorderTraversal(root, inorderList);
    }

    private boolean inorderTraversal(TreeNode root, List<Integer> inorderList) {
        if (root == null) {
            return true;
        }
        if (!inorderTraversal(root.left, inorderList)) {
            return false;
        }
        if (inorderList.size() > 0 && inorderList.get(inorderList.size() - 1) >= root.val) {
            return false;
        }
        inorderList.add(root.val);
        return inorderTraversal(root.right, inorderList);
    }
}
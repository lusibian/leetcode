package lusibian.leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class _101SymmetricTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 迭代，维持两个队列，按顺序存放对应的左子树和右子树
    public boolean isSymmetric1(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        LinkedList<TreeNode> leftQueue = new LinkedList<>();
        LinkedList<TreeNode> rightQueue = new LinkedList<>();
        leftQueue.push(root.left);
        rightQueue.push(root.right);
        TreeNode leftNode;
        TreeNode rightNode;
        while (!leftQueue.isEmpty()) {
            leftNode = leftQueue.poll();
            rightNode = rightQueue.poll();
            if (leftNode == null && rightNode == null) {
                continue;
            }
            if (leftNode == null || rightNode == null) {
                return false;
            }
            if (leftNode.val != rightNode.val) {
                return false;
            }

            leftQueue.push(leftNode.left);
            rightQueue.push(rightNode.right);
            leftQueue.push(rightNode.left);
            rightQueue.push(leftNode.right);
        }
        return true;
    }

    // 递归
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.val != node2.val) {
            return false;
        }
        return isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
    }
}

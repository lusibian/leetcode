package lusibian.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _102BinaryTreeLevelOrderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 递归
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> levelOrderTraversalList = new ArrayList<>();
        levelOrderHelper(root, levelOrderTraversalList, 0);
        return levelOrderTraversalList;
    }

    private void levelOrderHelper(TreeNode node, List<List<Integer>> levelOrderTraversalList, Integer currentLevel) {
        if (node == null) {
            return;
        }
        if (levelOrderTraversalList.size() <= currentLevel) {
            levelOrderTraversalList.add(new ArrayList<>());
        }
        levelOrderTraversalList.get(currentLevel).add(node.val);
        levelOrderHelper(node.left, levelOrderTraversalList, currentLevel + 1);
        levelOrderHelper(node.right, levelOrderTraversalList, currentLevel + 1);
    }

    // 迭代，使用队列记录
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> levelOrderTraversalList = new ArrayList<>();
        if (root == null) {
            return levelOrderTraversalList;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        int currentLevel = 0;
        int currentLevelNumberSizeInQueue = 1;
        int nextLevelSizeInQueue = 0;
        levelOrderTraversalList.add(new ArrayList<>());
        while (!queue.isEmpty()) {
            if (currentLevelNumberSizeInQueue == 0) {
                currentLevel++;
                currentLevelNumberSizeInQueue = nextLevelSizeInQueue;
                nextLevelSizeInQueue = 0;
                levelOrderTraversalList.add(new ArrayList<>());
            }
            TreeNode node = queue.poll();
            levelOrderTraversalList.get(currentLevel).add(node.val);
            if (node.left != null) {
                queue.add(node.left);
                nextLevelSizeInQueue++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLevelSizeInQueue++;
            }
            currentLevelNumberSizeInQueue--;
        }
        return levelOrderTraversalList;
    }
}

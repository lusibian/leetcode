package lusibian.leetcode.tree;

public class _236LowestCommonAncestorOfABinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode lowestCommonAncestorNode;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestorNode = null;
        lowestCommonAncestorHelper(root, p, q);
        return lowestCommonAncestorNode;
    }

    private int lowestCommonAncestorHelper(TreeNode node, TreeNode p, TreeNode q) {
        if (lowestCommonAncestorNode != null || node == null) {
            return 0;
        }
        int totalFound = 0;
        if (node == p || node == q) {
            totalFound++;
        }
        totalFound += lowestCommonAncestorHelper(node.left, p, q);
        totalFound += lowestCommonAncestorHelper(node.right, p, q);
        if (totalFound == 2) {
            lowestCommonAncestorNode = node;
        }
        if (totalFound > 0) {
            return 1;
        }
        return 0;
    }
}

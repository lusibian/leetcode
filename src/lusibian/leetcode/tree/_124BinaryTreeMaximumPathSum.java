package lusibian.leetcode.tree;

public class _124BinaryTreeMaximumPathSum {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 在二叉树中，记一条路径path最顶端的节点为topOfPath
    // path中所有节点都在以topOfPath为根的二叉树中，且path中包含topOfPath
    // 则pathSum可视为以下三部分的和：
    //      1. topOfPath的左子树中某个节点到topOfPath左儿子的pathSum（可为空），记为leftChildPathSumOfTopNode
    //      2. topOfPath的右子树中的某个节点到topOfPath右儿子的pathSum（可为空），记为rightChildPathSumOfTopNode
    //      3. topOfPath本身的值
    // 对每个topOfPath： pathSumOfTopNode_max = leftChildPathSumOfTopNode_max + rightChildPathSumOfTopNode_max + topOfPath
    // 则最大路径和问题转化为：求以每个节点为topOfPath的pathSumOfTopNode_max中的最大值
    //
    // pathSumOfTopNode_max使用递归的方式求解
    // 对每个节点A，向下递归获取其leftChildPathSumOfTopNode_max、rightChildPathSumOfTopNode_max
    // 以A为topOfPath的最大路径和：leftChildPathSumOfTopNode_max + rightChildPathSumOfTopNode_max + A.val
    // 然后将A所在子树的childPathSumOfTopNode_max返回给A的父节点，其是以下两者中最大值：
    //      1. Max(leftChildPathSumOfTopNode_max, rightChildPathSumOfTopNode_max) + A.val
    //      2. 0
    private int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        findMaxPathSum(root);
        return maxPathSum;
    }

    private int findMaxPathSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftChildPathSum = findMaxPathSum(node.left);
        int rightChildPathSum = findMaxPathSum(node.right);
        int pathSum = leftChildPathSum + rightChildPathSum + node.val;
        if (pathSum > maxPathSum) {
            maxPathSum = pathSum;
        }
        return Math.max(Math.max(leftChildPathSum, rightChildPathSum) + node.val, 0);
    }
}

package lusibian.leetcode.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _94BinaryTreeInorderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 递归版
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> traversalList = new ArrayList<>();
        inorderTraversal1(root, traversalList);
        return traversalList;
    }

    private void inorderTraversal1(TreeNode root, List<Integer> traversalList) {
        if(root == null) {
            return;
        }
        inorderTraversal1(root.left, traversalList);
        traversalList.add(root.val);
        inorderTraversal1(root.right, traversalList);
    }

    // 非递归版，使用栈
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> traversalList = new ArrayList<>();
        if (root == null) {
            return traversalList;
        }
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);
        boolean leftHasPushedFlag = false;  //记录当前栈顶节点的左儿子是否入过栈
        TreeNode currentTop;
        while(!nodeStack.empty()) {
            currentTop = nodeStack.peek();
            if(!leftHasPushedFlag && currentTop.left != null) {
                nodeStack.push(currentTop.left);
            } else {
                nodeStack.pop();
                traversalList.add(currentTop.val);
                if(currentTop.right != null) {
                    nodeStack.push(currentTop.right);
                    leftHasPushedFlag = false;  // 有新节点入栈，新栈顶节点的左儿子未入栈
                } else {
                    leftHasPushedFlag = true;   // 无新节点入栈，已在栈中的所有节点的左儿子都已入过栈
                }
            }
        }
        return traversalList;
    }
}

package lusibian.leetcode.array;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
//    }
//
//    private TreeNode buildTree(int[] preorder, int[] inorder, int startPre, int endPre, int startIn, int endIn) {
//        if(startPre > endPre) {
//            return null;
//        }
//        TreeNode node = new TreeNode(preorder[startPre]);
//        for(int i = startIn; i <= endIn; i++) {
//            if(inorder[i] == preorder[startPre]) {
//                int step = i - startIn;
//                node.left = buildTree(preorder, inorder, startPre + 1, startPre + step, startIn, i - 1);
//                node.right = buildTree(preorder, inorder, startPre + step + 1, endPre, i + 1, endIn);
//            }
//        }
//        return node;
//    }


    int pre=0;
    int in=0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder,inorder,Integer.MIN_VALUE);
    }

    public TreeNode build(int[] preorder, int[] inorder,int stop){
        if(pre>=preorder.length)return null;

        if(inorder[in]==stop){
            in++;
            return null;
        }

        TreeNode root = new TreeNode(preorder[pre++]);
        root.left=build(preorder,inorder,root.val);
        root.right = build(preorder,inorder,stop);
        return root;
    }
}

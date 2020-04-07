package lusibian.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class _297SerializeAndDeserializeBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode root = codec.deserialize("[1,2,3,null,null,4,5]");
        System.out.println(root);
        System.out.println(codec.serialize(root));
    }
    public static class Codec {

        // Encodes a tree to a single string.
        public static String serialize(TreeNode root) {
            if (root == null) {
                return "[]";
            }
            StringBuilder serializedStringBuilder = new StringBuilder();
            serializedStringBuilder.append("[").append(root.val);
            Queue<TreeNode> queue = new LinkedList<>();
            int notNullInQueue = 0;
            if (root.left != null) {
                notNullInQueue++;
            }
            if (root.right != null) {
                notNullInQueue++;
            }
            queue.add(root.left);
            queue.add(root.right);
            while (!queue.isEmpty() && notNullInQueue > 0) {
                TreeNode node = queue.poll();
                if(node != null) {
                    notNullInQueue--;
                    serializedStringBuilder.append(",").append(node.val);

                    if (node.left != null) {
                        notNullInQueue++;
                    }
                    if (node.right != null) {
                        notNullInQueue++;
                    }
                    queue.add(node.left);
                    queue.add(node.right);
                } else {
                    serializedStringBuilder.append(",null");
                }
            }
            serializedStringBuilder.append("]");
            return serializedStringBuilder.toString();
        }

        // Decodes your encoded data to tree.
        public static TreeNode deserialize(String data) {
            if(data.length() <= 2) {
                return null;
            }
            String[] strArray = data.substring(1, data.length() - 1).split(",");
            if (strArray.length == 0 || strArray[0].equals("null")) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(strArray[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            for (int i = 1; i < strArray.length; i+=2) {
                TreeNode parentNode = queue.poll();
                if (!strArray[i].equals("null")) {
                    parentNode.left = new TreeNode(Integer.valueOf(strArray[i]));
                    queue.add(parentNode.left);
                }
                if (i + 1 < strArray.length && !strArray[i + 1].equals("null")) {
                    parentNode.right = new TreeNode(Integer.valueOf(strArray[i + 1]));
                    queue.add(parentNode.right);
                }
            }
            return root;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
}

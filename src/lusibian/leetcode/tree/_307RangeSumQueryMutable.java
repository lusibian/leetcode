package lusibian.leetcode.tree;

public class _307RangeSumQueryMutable {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 9, 5, 7, 3};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(4, 4));
        System.out.println(numArray.sumRange(2, 4));
        System.out.println(numArray.sumRange(3, 3));
        numArray.update(0, 2);
        numArray.update(1, -1);
        numArray.update(2, 3);
        numArray.update(0, 5);
        numArray.update(0, -4);
        System.out.println(numArray.sumRange(0, 2));
    }

    // 线段树
    static class NumArray {
        int[] tree;
        int n;

        public NumArray(int[] nums) {
            if (nums.length > 0) {
                n = nums.length;
                tree = new int[n * 2];
                buildTree(nums);
            }
        }

        public void update(int i, int val) {
            int temp = val - tree[i + n];
            tree[i + n] = val;
            int parent = (i + n) / 2;
            while (parent > 0) {
                tree[parent] += temp;
                parent /= 2;
            }
        }

        // 从叶节点往上
        // 如果left是其父节点的右儿子，则将left的值累加，left++
        // 如果right是其父节点的左儿子，则将right的值累加，right--
        // left，right向上一层，到他们的父节点
        public int sumRange(int i, int j) {
            int l = i + n;
            int r = j + n;
            int sum = 0;
            while (l <= r) {
                if ((l % 2) == 1) {
                    sum += tree[l];
                    l++;
                }
                if ((r % 2) == 0) {
                    sum += tree[r];
                    r--;
                }
                l /= 2;
                r /= 2;
            }
            return sum;
        }

        private void buildTree(int[] nums) {
            for (int i = n, j = 0; i < 2 * n; i++, j++)
                tree[i] = nums[j];
            for (int i = n - 1; i > 0; --i)
                tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }
}

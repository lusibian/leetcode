package lusibian.leetcode.array;

public class _4MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{2, 3};
        _4MedianOfTwoSortedArrays temp = new _4MedianOfTwoSortedArrays();
        System.out.println(temp.findMedianSortedArrays(nums1, nums2));
    }

    // 二分法
    // nums1、nums2的长度分别为m、n，m <= n
    // nums1、nums2中分别存在下标i、j，i + j = (m + n + 1) / 2
    // i、j将数组分成数量相当的左右两半
    // 算法的目标是找到i、j，其划分的数组左半部分的所有数均不大于右半部分的所有数
    // 由于两个数组均有序，只需满足 nums1[i-1] <= nums[j] 且 nums2[j-1] <= nums[i] 时
        // i、j划分的数组左半部分的所有数一定不大于右半部分的所有数
    // 考虑i或j为0的情况：
        // 若i=0，则只需满足 nums2[j-1] <= nums[i]
        // 若j=0，则只需满足 nums1[i-1] <= nums[j]
    // 查找过程，对较小的数组nums1进行二分查找，时间复杂度O(lg(min(m,n)))，空间复杂度O(1)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int m = nums1.length;
        int n = nums2.length;
        // 对较小的数组进行二分
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && nums2[j - 1] > nums1[i]) {
                // j左侧的数比i大，即由i、j划分的数组左半部分存在数大于i，i比中位数小，向i的右侧找
                iMin = i + 1;
            } else if (i > iMin && nums1[i - 1] > nums2[j]) {
                // i左侧的数小于j，即由i、j划分的数组左半部分存在数大于j，j比中位数小，向j的右侧找
                // i + j = (m + n + 1) / 2，j右移，即i左移，向i的左侧找
                iMax = i - 1;
            } else {
                int maxLeft;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}

package lusibian.leetcode.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _315CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2};
        _315CountOfSmallerNumbersAfterSelf temp = new _315CountOfSmallerNumbersAfterSelf();
        List<Integer> countList = temp.countSmaller(nums);
        for (Integer count : countList) {
            System.out.println(count);
        }
    }

    // 归并+索引数组
    //算法关键点：
        //归并
            //在归并排序的过程中，统计逆序对的个数
        //索引数组
            //需要将每个数字的逆序对个数记录在其初始位置上
            //而数字在排序后，其位置会变化
            //因此，使用索引数组记录其初始位置，然后对索引数组进行排序，原数组不变
            //使用索引数组排序，在记录统计结果时，能根据索引将count值填入到对应的位置上
            //例：
            //原数组[4,2,1,3]，其当前索引数组为[0,1,2,3]
            //排序后的索引数组为[2,1,3,0]
    public List<Integer> countSmaller(int[] nums) {
        int[] countArray = new int[nums.length];    // 逆序对计数
        int[] idxArray = new int[nums.length];  // 索引数组
        int[] assistArray = new int[nums.length];   // 辅助数组，用于暂时记录排序结果
        for (int i = 0; i < idxArray.length; i++) {
            idxArray[i] = i;
        }
        for (int interval = 1; interval < idxArray.length; interval *= 2) {
            int start1 = 0;
            int start2 = interval;
            int end2 = start2 + interval;
            int pointer1 = start1;
            int pointer2 = start2;
            int assistPointer = start1; // assistArray的写入指针，用于暂时记录排序结果
            while (start2 < idxArray.length) {
                // 防止归并的后半部分超出数组边界
                if (end2 > idxArray.length) {
                    end2 = idxArray.length;
                }
                while (pointer1 < start2 && pointer2 < end2) {
                    if (nums[idxArray[pointer1]] <= nums[idxArray[pointer2]]) {

                        // 统计逆序对
                        countArray[idxArray[pointer1]] += pointer2 - start2;

                        assistArray[assistPointer] = idxArray[pointer1];
                        assistPointer++;
                        pointer1++;
                    } else {
                        assistArray[assistPointer] = idxArray[pointer2];
                        assistPointer++;
                        pointer2++;
                    }
                }
                while (pointer1 < start2) {

                    // 统计逆序对
                    countArray[idxArray[pointer1]] += end2 - start2;

                    assistArray[assistPointer] = idxArray[pointer1];
                    assistPointer++;
                    pointer1++;
                }
                while (pointer2 < end2) {
                    assistArray[assistPointer] = idxArray[pointer2];
                    assistPointer++;
                    pointer2++;
                }
                for (int i = start1; i < end2; i++) {
                    idxArray[i] = assistArray[i];
                }

                // 继续下一轮归并，初始化
                start1 += interval * 2;
                start2 += interval * 2;
                end2 += interval * 2;
                pointer1 = start1;
                pointer2 = start2;
                assistPointer = start1;
            }
        }
        return Arrays.stream(countArray).boxed().collect(Collectors.toList());
    }
}

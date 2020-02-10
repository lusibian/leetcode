package lusibian.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class _3LongestSubstringWithoutRepeatingCharacters {

    // 滑动窗口，当前窗口为(left，i]
    // 使用map记录字符最近一次出现的位置
    // 若当前字符最近一次出现的位置在left的右边，则将left右移
    // 将字符串长度记为n，字符集的大小记为m
    // 时间O(n)，空间O(min(m,n))
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> indexMap = new HashMap<>();
        char[] chars = s.toCharArray();
        int maxLength = 0;
        int left = -1;
        for (int i = 0; i < chars.length; i++) {
            if (indexMap.containsKey(chars[i])) {
                left = Math.max(indexMap.get(chars[i]), left);
            }
            maxLength = Math.max(i - left, maxLength);
            indexMap.put(chars[i], i);
        }
        return maxLength;
    }
}

package lusibian.leetcode.array;

public class ValidAnagram {
    // 计数排序，或者说是 hash表
    public boolean isAnagram(String s, String t) {
        int[] charCounts = new int[26];
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        for (char c : sArray) {
            charCounts[c - 'a']++;
        }
        for (char c : tArray) {
            charCounts[c - 'a']--;
        }
        for (int charCount : charCounts) {
            if(charCount != 0) {
                return false;
            }
        }
        return true;
    }
}

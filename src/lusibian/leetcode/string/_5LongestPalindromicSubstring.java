package lusibian.leetcode.string;

public class _5LongestPalindromicSubstring {
    // 中心扩展法
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length <= 1) {
            return s;
        }
        int left;
        int right;
        String longestPalindoromeStr = "";
        for (int i = 0; i < length; i++) {
            left = i;
            right = i + 1;
            while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if (right - left - 1 > longestPalindoromeStr.length()) {
                longestPalindoromeStr = s.substring(left + 1, right);
            }
            left = i - 1;
            right = i + 1;
            while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            if (right - left - 1 > longestPalindoromeStr.length()) {
                longestPalindoromeStr = s.substring(left + 1, right);
            }
        }
        return longestPalindoromeStr;
    }
}

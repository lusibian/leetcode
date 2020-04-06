package lusibian.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class _76MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (t.length() == 0 || s.length() == 0) {
            return "";
        }
        Map<Character, Integer> charCountT = new HashMap<>();
        Map<Character, Integer> charCountS = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            charCountT.put(t.charAt(i), 1 + charCountT.getOrDefault(t.charAt(i), 0));
        }
        int left = 0;
        int right = -1;
        int totalKeyT = charCountT.size();
        int sufficientKeyInWindow = 0;
        while (sufficientKeyInWindow < totalKeyT && ++right < s.length()) {
            if (charCountT.containsKey(s.charAt(right))) {
                charCountS.put(s.charAt(right), 1 + charCountS.getOrDefault(s.charAt(right), 0));
                if (charCountS.get(s.charAt(right)).equals(charCountT.get(s.charAt(right)))) {
                    sufficientKeyInWindow++;
                }
            }
        }
        if (sufficientKeyInWindow < totalKeyT) {
            return "";
        }
        String minWindow = null;
        while (right < s.length()) {
            while (!charCountT.containsKey(s.charAt(left)) ||
                    charCountS.get(s.charAt(left)) > charCountT.get(s.charAt(left))) {
                if (charCountS.containsKey(s.charAt(left))) {
                    charCountS.put(s.charAt(left), charCountS.get(s.charAt(left)) - 1);
                }
                left++;
            }
            if (minWindow == null || minWindow.length() > right - left + 1) {
                minWindow = s.substring(left, right + 1);
            }
            while (++right < s.length()) {
                if (charCountS.containsKey(s.charAt(right))) {
                    charCountS.put(s.charAt(right), 1 + charCountS.get(s.charAt(right)));
                }
                if (s.charAt(right) == s.charAt(left)) {
                    break;
                }
            }
        }
        return minWindow;
    }
}

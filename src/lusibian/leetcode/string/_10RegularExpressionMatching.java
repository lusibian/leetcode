package lusibian.leetcode.string;

public class _10RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }
        if (p.charAt(0) == '*') {
            return false;
        }
        int lengthS = s.length();
        boolean[] dp = new boolean[lengthS + 1];
        dp[0] = true;
        for (int i = 0; i < p.length(); i++) {
            boolean flag = false;
            if (p.charAt(i) == '*') {
                continue;
            } else if (p.charAt(i) == '.') {
                if (i + 1 < p.length() && p.charAt(i + 1) == '*') {
                    for (int j = 0; j <= lengthS; j++) {
                        if (dp[j]) {
                            flag = true;
                        }
                        dp[j] = flag;
                    }
                } else {
                    for (int j = lengthS; j > 0; j--) {
                        dp[j] = dp[j - 1];
                        if (dp[j]) {
                            flag = true;
                        }
                    }
                    dp[0] = false;
                }
            } else {
                if (i + 1 < p.length() && p.charAt(i + 1) == '*') {
                    for (int j = 0; j <= lengthS; j++) {
                        dp[j] = dp[j] || j > 0 && dp[j - 1] && s.charAt(j - 1) == p.charAt(i);
                        if (dp[j]) {
                            flag = true;
                        }
                    }
                } else {
                    for (int j = lengthS; j > 0; j--) {
                        dp[j] = dp[j - 1] && s.charAt(j - 1) == p.charAt(i);
                        if (dp[j]) {
                            flag = true;
                        }
                    }
                    dp[0] = false;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return dp[lengthS];
    }
}

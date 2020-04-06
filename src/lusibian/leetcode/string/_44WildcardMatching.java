package lusibian.leetcode.string;

public class _44WildcardMatching {
    public boolean isMatch(String s, String p) {
        int lengthS = s.length();
        boolean[] dp = new boolean[lengthS + 1];
        dp[0] = true;
        for (int i = 0; i < p.length(); i++) {
            boolean flag = false;
            if (p.charAt(i) == '*') {
                for (int j = 0; j <= lengthS; j++) {
                    if (dp[j]) {
                        flag = true;
                    }
                    dp[j] = flag;
                }
            } else if (p.charAt(i) == '?') {
                for (int j = lengthS; j > 0; j--) {
                    dp[j] = dp[j - 1];
                    if (dp[j]) {
                        flag = true;
                    }
                }
                dp[0] = false;
            } else {
                for (int j = lengthS; j > 0; j--) {
                    dp[j] = dp[j - 1] && s.charAt(j - 1) == p.charAt(i);
                    if (dp[j]) {
                        flag = true;
                    }
                }
                dp[0] = false;
            }
            if (!flag) {
                return false;
            }
        }
        return dp[lengthS];
    }
}

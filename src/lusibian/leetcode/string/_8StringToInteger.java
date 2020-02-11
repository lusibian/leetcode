package lusibian.leetcode.string;

public class _8StringToInteger {
    public static void main(String[] args) {
        String s = "2147483648";
        _8StringToInteger temp = new _8StringToInteger();
        System.out.println(temp.myAtoi(s));
    }
    public int myAtoi(String str) {
        Long num = 0L;
        char[] chars = str.toCharArray();
        int i = 0;
        while (i < chars.length && chars[i] == ' ') {
            i++;
        }
        boolean negativeFlag = false;
        if (i < chars.length) {
            if(chars[i] == '-') {
                negativeFlag = true;
                i++;
            } else if (chars[i] == '+') {
                i++;
            }
        }
        while (i < chars.length && chars[i] >= '0' && chars[i] <= '9') {
            num *= 10;
            num += chars[i] - '0';
            i++;
            if (num > Integer.MAX_VALUE) {
                if (negativeFlag) {
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
        }
        if (negativeFlag) {
            num = -num;
        }
        return Integer.valueOf(num.toString());
    }
}

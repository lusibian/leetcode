package lusibian.leetcode.stack;

public class _394DecodeString {
    public static void main(String[] args) {
        String s = "3[a2[c]]";
        _394DecodeString temp = new _394DecodeString();
        System.out.println(temp.decodeString(s));
    }

    private int currentCharPointer = 0;

    public String decodeString(String s) {
        char[] chars = s.toCharArray();
        StringBuilder decodedString = new StringBuilder();
        while (currentCharPointer < chars.length) {
            // 若遍历未结束，继续解析
            decodedString.append(decodeString(chars));
        }
        return decodedString.toString();
    }

    private String decodeString(char[] chars) {
        StringBuilder decodedString = new StringBuilder();
        StringBuilder repeatString = new StringBuilder();
        int copyTimes = 0;  // 记录重复次数
        // 插入不在中括号中的字母
        while (currentCharPointer < chars.length && chars[currentCharPointer] >= 'a' && chars[currentCharPointer] <= 'z') {
            decodedString.append(chars[currentCharPointer]);
            currentCharPointer++;
        }
        // 判断遍历是否结束
        if (currentCharPointer >= chars.length) {
            return decodedString.toString();
        }
        // 记录重复次数
        while (chars[currentCharPointer] != '[') {
            copyTimes *= 10;
            copyTimes += chars[currentCharPointer] - '0';
            currentCharPointer++;
        }
        currentCharPointer++; // 跳过'['
        // 解析需重复的字符串，记录于repeatString，可能会递归
        while (chars[currentCharPointer] != ']') {
            if (chars[currentCharPointer] >= '0' && chars[currentCharPointer] <= '9') {
                // 遇到数字，递归进行解析
                repeatString.append(decodeString(chars));
            } else {
                repeatString.append(chars[currentCharPointer]);
                currentCharPointer++;
            }
        }
        currentCharPointer++; // 跳过']'
        String copyString = repeatString.toString();
        for (int i = 0; i < copyTimes; i++) {
            decodedString.append(copyString);
        }
        return decodedString.toString();
    }
}

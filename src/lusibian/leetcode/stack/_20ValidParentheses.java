package lusibian.leetcode.stack;

import java.util.Stack;

public class _20ValidParentheses {
    // 栈
    public boolean isValid(String s) {
        Stack<Character> leftParenthesisStack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == '(' || aChar == '{' || aChar == '[') {
                leftParenthesisStack.push(aChar);
            } else {
                if (leftParenthesisStack.empty()) {
                    return false;
                }
                char leftParenthesis = leftParenthesisStack.pop();
                if (!((aChar == ')' && leftParenthesis == '(')
                        || (aChar == ']' && leftParenthesis == '[')
                        || (aChar == '}' && leftParenthesis == '{'))) {
                    return false;
                }
            }
        }
        return leftParenthesisStack.empty();
    }
}

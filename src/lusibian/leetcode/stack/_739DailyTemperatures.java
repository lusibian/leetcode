package lusibian.leetcode.stack;

import java.util.Stack;

public class _739DailyTemperatures {
    // 小顶栈
    // 若新元素比栈顶大，则栈顶出栈，直到栈为空，或者栈顶元素比新元素大时，新元素入栈
    // 对每个出栈元素，新元素就是它等待的更温暖的那天
    public int[] dailyTemperatures(int[] T) {
        int[] warmerDayAfterToday = new int[T.length];
        Stack<Integer> idxStack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!idxStack.empty() && T[idxStack.peek()] < T[i]) {
                int topOld = idxStack.pop();
                warmerDayAfterToday[topOld] = i - topOld;
            }
            idxStack.push(i);
        }
        while (!idxStack.empty()) {
            int topOld = idxStack.pop();
            warmerDayAfterToday[topOld] = 0;
        }
        return warmerDayAfterToday;
    }
}

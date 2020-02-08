package lusibian.leetcode.stack;

import java.util.Stack;

public class _155MinStack {
    // 两个栈，一个辅助栈用来存放最小值
    class MinStack {
        Stack<Integer> dataStack;
        Stack<Integer> assistStack;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            dataStack = new Stack<>();
            assistStack = new Stack<>();
        }

        public void push(int x) {
            dataStack.push(x);
            if (assistStack.empty() || x <= assistStack.peek()) {
                assistStack.push(x);
            }
        }

        public void pop() {
            int top = dataStack.pop();
            if (top == assistStack.peek()) {
                assistStack.pop();
            }
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            return assistStack.peek();
        }
    }
}

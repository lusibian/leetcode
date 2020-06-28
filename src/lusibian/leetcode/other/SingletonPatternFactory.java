package lusibian.leetcode.other;

public class SingletonPatternFactory {
    // 单例
    // 静态内部类实现
    private static class InstanceHolder {
        public static final SingletonPatternInstance instance = new SingletonPatternInstance();
    }

    public SingletonPatternInstance getInstance() {
        return InstanceHolder.instance;
    }
}

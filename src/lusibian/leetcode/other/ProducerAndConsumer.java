package lusibian.leetcode.other;

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProducerAndConsumer {
    // 一个生产者，三个消费者，读文件
    // 使用阻塞队列
    private static BlockingQueue<String> lineQueue = new ArrayBlockingQueue<String>(10);
    private volatile static boolean endFLag = false;

    public static void main(String[] args) {
        ProducerAndConsumer test1 = new ProducerAndConsumer();
        new Thread(test1.new Producer()).start();
        new Thread(test1.new Consumer()).start();
        new Thread(test1.new Consumer()).start();
        new Thread(test1.new Consumer()).start();
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            try {
                String path = "/home/sumeng/下载/staging_10.136.20.51%3A20079_1583474347148_1_script_error.log";
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                    lineQueue.put(str);
                }
                System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + lineQueue.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
            endFLag = true;
            System.out.println("生产者退出");
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                while (lineQueue.size() > 0 || !endFLag) {
                    String str = lineQueue.poll(1, TimeUnit.SECONDS);
                    if (str != null) {
                        System.out.println("消费者消费 ： " + str);
                    }
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + lineQueue.size());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("消费者退出");
        }
    }

}

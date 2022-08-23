package thread_concurrentCommonClass;

import utils.SleepHelper;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/24 2:48 上午
 */
public class LinkedBlockingQueueTest {
    static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    blockingQueue.put("a" + i);
                    SleepHelper.sleepMilliSeconds(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (; ; ) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " take-" + blockingQueue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Thread2").start();
        }
    }
}

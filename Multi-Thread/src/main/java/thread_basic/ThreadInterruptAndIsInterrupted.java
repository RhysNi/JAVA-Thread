package thread_basic;

import utils.SleepHelper;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/9 1:32 上午
 */
public class ThreadInterruptAndIsInterrupted {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (; ; ) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread is interrupted...");
                    System.out.println(Thread.currentThread().isInterrupted());
                    break;
                }
            }
        });
        thread.start();
        SleepHelper.sleepSeconds(2);
        thread.interrupt();
    }
}

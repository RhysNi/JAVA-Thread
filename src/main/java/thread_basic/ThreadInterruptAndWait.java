package thread_basic;

import utils.SleepHelper;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/9 2:03 上午
 */
public class ThreadInterruptAndWait {
    public static Object o = new Object();

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            synchronized (o) {
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    out.println("Thread is interrupted...");
                    out.println(Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();
        SleepHelper.sleepSeconds(5);
        thread.interrupt();
    }
}

package thread_synchronized;


import utils.SleepHelper;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/10 11:47 下午
 */
public class SynchronizedReentrant {
    synchronized void method1() {
        out.println(Thread.currentThread().getName() + " method1 start...");
      SleepHelper.sleepSeconds(1);
        method2();
        out.println(Thread.currentThread().getName() + " method1 end...");
    }

    synchronized void method2() {
        out.println(Thread.currentThread().getName() + " method2 start...");
        SleepHelper.sleepSeconds(2);
        out.println(Thread.currentThread().getName() + " method2 end...");
    }

    public static void main(String[] args) {
        new Thread(new SynchronizedReentrant()::method1, "thread1").start();
    }
}

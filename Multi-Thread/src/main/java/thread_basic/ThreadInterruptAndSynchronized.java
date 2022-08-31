package thread_basic;

import utils.SleepHelper;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/9 2:13 上午
 */
public class ThreadInterruptAndSynchronized {
    public static Object o = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (o){
                SleepHelper.sleepSeconds(10);
            }
        });
        thread1.start();
        SleepHelper.sleepSeconds(1);

        Thread thread2 = new Thread(() -> {
            synchronized (o){}
            System.out.println("thread2 end...");
        });

        thread2.start();
        SleepHelper.sleepSeconds(1);

        //不会对正在竞争锁的线程产生影响，也正说明interrupt并非真正打断线程，只是设置标志位
        thread2.interrupt();
    }
}

package thread_basic;

import utils.SleepHelper;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/9 1:35 上午
 */
public class ThreadInterruptAndInterrupted {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            for (; ; ) {
                if (Thread.interrupted()) {
                    System.out.println("Thread is interrupted...");
                    System.out.println(Thread.interrupted());
                }
            }
        });
        thread.start();
        SleepHelper.sleepSeconds(2);
        thread.interrupt();

        //static interrupt ： 当前线程查询并重置标志位,由于是静态方法，可以直接调用（谁调就查谁）这边自然是查主线程
        System.out.println("main: " + thread.interrupted());
    }
}

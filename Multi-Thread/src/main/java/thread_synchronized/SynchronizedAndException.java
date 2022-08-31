package thread_synchronized;


import utils.SleepHelper;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/10 11:47 下午
 */
public class SynchronizedAndException {
    int count = 0;

    public static void main(String[] args) {
        SynchronizedAndException test = new SynchronizedAndException();
        new Thread(test::method1, "thread1").start();
        new Thread(test::method1, "thread2").start();
    }

    synchronized void method1() {
        out.println(Thread.currentThread().getName() + " method1 start...");
        while (true) {
            count++;
            out.println(Thread.currentThread().getName() + " count = " + count);
            SleepHelper.sleepSeconds(1);
            if (count == 5) {
                //抛出ArithmeticException异常，锁被释放
                //如果catch异常则锁不被释放，循环继续
                int i = 1 / 0;
                out.println(i);
            }
        }
    }
}

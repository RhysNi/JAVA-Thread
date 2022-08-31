package thread_basic;

import utils.SleepHelper;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/9 2:13 上午
 */
public class ThreadInterruptAndReentrantLockInterruptibly {
    public static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            out.println("thread1 end...");
        });
        thread1.start();
        SleepHelper.sleepSeconds(1);

        //lockInterruptibly: 可以被打断的过程
        Thread thread2 = new Thread(() -> {
            out.println("thread2 start...");
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            out.println("thread2 end...");
        });

        thread2.start();
        SleepHelper.sleepSeconds(1);

        //不会对正在竞争锁的线程产生影响，也正说明interrupt并非真正打断线程，只是设置标志位
        thread2.interrupt();
    }
}

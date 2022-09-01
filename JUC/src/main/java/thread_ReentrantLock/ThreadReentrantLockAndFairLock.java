package thread_ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/16 1:20 上午
 */
public class ThreadReentrantLockAndFairLock extends Thread {
    //fair：true为公平锁
    public static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        ThreadReentrantLockAndFairLock fairLock = new ThreadReentrantLockAndFairLock();
        Thread thread1 = new Thread(fairLock);
        Thread thread2 = new Thread(fairLock);
        thread1.start();
        thread2.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                out.println(Thread.currentThread().getName() + " GET LOCK");
            } finally {
                lock.unlock();
            }
        }
    }
}

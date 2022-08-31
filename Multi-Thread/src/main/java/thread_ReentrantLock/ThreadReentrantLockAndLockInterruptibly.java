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
public class ThreadReentrantLockAndLockInterruptibly {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();
                out.println("thread1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                out.println("thread1 end");
            } catch (InterruptedException e) {
                out.println("thread1 Interrupted!!!");
            } finally {
                lock.unlock();
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                lock.lockInterruptibly();
                out.println("thread2 start");
                TimeUnit.SECONDS.sleep(5);
                out.println("thread2 end");
            } catch (InterruptedException e) {
                out.println("thread2 Interrupted!!!");
            } finally {
                lock.unlock();
            }
        });
        thread2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.interrupt();
    }
}

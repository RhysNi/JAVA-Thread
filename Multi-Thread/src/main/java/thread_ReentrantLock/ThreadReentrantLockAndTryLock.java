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
public class ThreadReentrantLockAndTryLock {
    Lock lock = new ReentrantLock();

    void method1() {
        try {
            lock.lock();
            for (int i = 0; i < 3; i++) {
                TimeUnit.SECONDS.sleep(1);
                out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void method2() {
        boolean locked = false;
        try {
            //尝试5秒内获得锁，method1中3秒后释放锁，因此method2可以在5秒内获得获得这把锁
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            out.println("method2..." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadReentrantLockAndTryLock reentrantLock = new ThreadReentrantLockAndTryLock();
        new Thread(reentrantLock::method1).start();
        TimeUnit.SECONDS.sleep(1);
        //与synchronized相同，在不同线程中会等method1锁释放后method2才会获得这把锁
        new Thread(reentrantLock::method2).start();
    }
}

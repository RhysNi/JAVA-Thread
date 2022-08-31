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
public class ThreadReentrantLock {
    Lock lock = new ReentrantLock(true);

    void method1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
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
        try {
            lock.lock();
            out.println("method2...");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadReentrantLock reentrantLock = new ThreadReentrantLock();
        new Thread(reentrantLock::method1).start();
        TimeUnit.SECONDS.sleep(1);
        //与synchronized相同，在不同线程中会等method1锁释放后method2才会获得这把锁
        new Thread(reentrantLock::method2).start();
    }
}

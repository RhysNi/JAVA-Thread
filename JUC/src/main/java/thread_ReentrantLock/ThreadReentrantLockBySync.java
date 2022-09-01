package thread_ReentrantLock;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/16 1:20 上午
 */
public class ThreadReentrantLockBySync {
    synchronized void method1() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            out.println(i);
            if (i == 3) {
                //由于这两个synchronized处于同一个线程内，所以是可重入的，会调用method2
                method2();
            }
        }
    }

    synchronized void method2() {
        out.println("method2...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadReentrantLockBySync reentrantLock = new ThreadReentrantLockBySync();
        new Thread(reentrantLock::method1).start();
        TimeUnit.SECONDS.sleep(1);
        // 由于不是同一个线程导致method1在执行的时候method2不会获得这把锁，也就是不可重入，所以在method1结束前不会调用method2
        // new Thread(reentrantLock::method2).start();
    }
}

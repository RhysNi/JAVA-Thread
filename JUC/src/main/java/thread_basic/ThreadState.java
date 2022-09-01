package thread_basic;

import utils.SleepHelper;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/9 12:15 上午
 */
public class ThreadState {
    public static void main(String[] args) throws Exception {
        //1: NEW
        //2: RUNNABLE
        //0 1 2
        //3: TERMINATED
        Thread thread1 = new Thread(() -> {
            out.println("2: " + Thread.currentThread().getState());
            for (int i = 0; i < 3; i++) {
                SleepHelper.sleepSeconds(1);
                out.print(i + " ");
            }
            //换行
            out.println();
        });

        out.println("1: " + thread1.getState());
        //thread1启动
        thread1.start();
        //等待thread1结束
        thread1.join();
        out.println("3: " + thread1.getState());

        //--------------------------------------------------------------------------------------------------
        //4: WAITING
        //thread2 Go On
        //5: TIMED_WAITING
        Thread thread2 = new Thread(() -> {
            //等待被唤醒
            LockSupport.park();
            out.println("thread2 Go On");
            SleepHelper.sleepSeconds(5);
        });

        thread2.start();
        SleepHelper.sleepSeconds(1);
        out.println("4: " + thread2.getState());

        LockSupport.unpark(thread2);
        SleepHelper.sleepSeconds(1);
        out.println("5: " + thread2.getState());

        //--------------------------------------------------------------------------------------------------
        //6: BLOCKED
        //thread3 Get Lock o
        Object o = new Object();
        Thread thread3 = new Thread(() -> {
            synchronized (o) {
                out.println("thread3 Get Lock:o");
            }
        });

        new Thread(() -> {
            synchronized (o) {
                SleepHelper.sleepSeconds(5);
            }
        }).start();
        //主线程睡一秒保证以上线程已经获得锁o
        SleepHelper.sleepSeconds(1);
        //启动thread3去竞争锁o
        thread3.start();
        SleepHelper.sleepSeconds(1);
        out.println("6: " + thread3.getState());

        //--------------------------------------------------------------------------------------------------
        //7: WAITING
        //thread4 Get Lock:o
        //JUC的锁由CAS来实现 -> '忙等待'：不会进入BLOCKED只会进入WAITING状态，只有synchronized这样的才会进入BLOCKED状态
        final Lock lock = new ReentrantLock();
        Thread thread4 = new Thread(() -> {
            lock.lock();
            out.println("thread4 Get Lock:o");
            lock.unlock();
        });

        new Thread(() -> {
            lock.lock();
            SleepHelper.sleepSeconds(5);
            lock.unlock();
        }).start();
        SleepHelper.sleepSeconds(1);

        thread4.start();
        SleepHelper.sleepSeconds(1);
        out.println("7: " + thread4.getState());


        //--------------------------------------------------------------------------------------------------
        //8: WAITING
        Thread thread5 = new Thread(LockSupport::park);
        thread5.start();
        SleepHelper.sleepSeconds(1);
        out.println("8: " + thread5.getState());
        LockSupport.unpark(thread5);
    }
}

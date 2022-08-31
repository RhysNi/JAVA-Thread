package thread_interview.question3;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/26 2:52 上午
 */
public class UseConditionLock {
    public static void main(String[] args) {
        char[] intArr = "1234567".toCharArray();
        char[] charArr = "ABCDEFG".toCharArray();
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                for (char c : charArr) {
                    System.out.print(c);
                    condition2.signal();
                    condition1.await();
                }
                //与Notify同理，为了结束线程的阻塞从而能顺利结束程序
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "thread1").start();

        new Thread(() -> {
            try {
                lock.lock();
                for (char i : intArr) {
                    System.out.print(i);
                    condition1.signal();
                    condition2.await();
                }
                condition1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "thread2").start();
    }
}

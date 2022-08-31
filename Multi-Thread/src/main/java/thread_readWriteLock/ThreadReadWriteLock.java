package thread_readWriteLock;

import utils.SleepHelper;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/16 3:48 上午
 */
public class ThreadReadWriteLock {
    private static int VALUE;
    static Lock lock = new ReentrantLock();

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {
        // Runnable readRunnable = () -> read(lock);
        // Runnable writeRunnable = () -> write(lock, new Random().nextInt());

        Runnable readRunnable = () -> read(readLock);
        Runnable writeRunnable = () -> write(writeLock, new Random().nextInt());

        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(writeRunnable).start();
        }
    }

    public static void read(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            out.println("read over...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int value) {
        try {
            lock.lock();
            Thread.sleep(1000);
            VALUE = value;
            out.println("write over...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

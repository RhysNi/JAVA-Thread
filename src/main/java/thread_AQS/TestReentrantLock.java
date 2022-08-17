package thread_AQS;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/17 2:57 上午
 */
public class TestReentrantLock {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        lock.unlock();
    }
}

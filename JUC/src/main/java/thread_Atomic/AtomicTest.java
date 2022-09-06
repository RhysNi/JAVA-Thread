package thread_Atomic;

import java.util.concurrent.CountDownLatch;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/9/6 10:27 下午
 */
public class AtomicTest {
    private static long NUM = 0L;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];

        CountDownLatch countDownLatch = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    //如果不加锁，那就会产生线程之间的竞争
                    //本应加到1000000的NUM却远远达不到目标值
                    synchronized (AtomicTest.class) {
                        NUM++;
                    }
                }
                countDownLatch.countDown();
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        countDownLatch.await();
        System.out.println(NUM);
    }
}

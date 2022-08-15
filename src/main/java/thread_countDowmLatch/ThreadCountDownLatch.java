package thread_countDowmLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/16 2:45 上午
 */
public class ThreadCountDownLatch {
    public static void main(String[] args) {
        usingCountDownLatch();
        usingJoin();
    }


    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result += j;
                }
                latch.countDown();
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End Latch");
    }

    private static void usingJoin() {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result += j;
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("End Join");
    }
}

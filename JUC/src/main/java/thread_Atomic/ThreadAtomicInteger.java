package thread_Atomic;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/11 10:59 下午
 */
public class ThreadAtomicInteger {
    AtomicInteger count = new AtomicInteger(0);

    void method() {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        ThreadAtomicInteger threadAtomicInteger = new ThreadAtomicInteger();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(threadAtomicInteger::method, "thread" + i));
        }

        threads.forEach((o) -> o.start());
        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(threadAtomicInteger.count);
    }
}

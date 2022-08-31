package thread_pool;

import utils.SleepHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/30 11:56 下午
 */
public class CachedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        System.out.println(executor);

        for (int i = 0; i < 2; i++) {
            executor.execute(() -> {
                SleepHelper.sleepMilliSeconds(500);
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(executor);
        SleepHelper.sleepSeconds(80);
        System.out.println(executor);
    }
}

package thread_pool;

import utils.SleepHelper;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/31 1:50 上午
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);
        executor.scheduleAtFixedRate(() -> {
            SleepHelper.sleepMilliSeconds(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName());
        }, 0, 500, TimeUnit.MILLISECONDS);
    }
}

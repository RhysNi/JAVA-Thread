package thread_pool;

import utils.SleepHelper;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/31 3:29 上午
 */
public class WorkStealingPoolTest {
    public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newWorkStealingPool();

        //可用的计算资源
        System.out.println(Runtime.getRuntime().availableProcessors());

        executor.execute(new R(1000));
        executor.execute(new R(2000));
        executor.execute(new R(2000));
        executor.execute(new R(2000));
        executor.execute(new R(2000));

        //由于产生的是精灵线程（守护线程/后台线程），主线程不阻塞的话，看不到输出
        System.in.read();
    }

    static class R implements Runnable {
        int time;

        R(int t) {
            this.time = t;
        }

        @Override
        public void run() {
            SleepHelper.sleepMilliSeconds(time);
            System.out.println(time + " " + Thread.currentThread().getName());
        }
    }
}

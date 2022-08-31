package thread_pool;

import utils.SleepHelper;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/30 9:47 下午
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            SleepHelper.sleepMilliSeconds(500);
            return 1000;
        });

        new Thread(task).start();

        //阻塞
        System.out.println(task.get());
    }
}

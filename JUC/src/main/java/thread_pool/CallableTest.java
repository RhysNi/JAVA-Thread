package thread_pool;

import java.util.concurrent.*;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/30 9:53 下午
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = () -> "Hello Callable";

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        Future<String> future = cachedThreadPool.submit(callable);

        System.out.println(future.get());
        cachedThreadPool.shutdown();
    }
}

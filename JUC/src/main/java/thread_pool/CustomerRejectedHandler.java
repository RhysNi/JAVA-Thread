package thread_pool;



import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/31 2:12 上午
 */
public class CustomerRejectedHandler {
    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4
                , 8
                , 0
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<>(6), new CustomerHandler());
        for (int i = 0; i < 50; i++) {
            executor.execute(() -> System.out.println(Thread.currentThread().getName()));
        }
        System.in.read();
    }

    static class CustomerHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //TODO:log(...rejected);
            System.out.println(r+"...rejected");
            //TODO:save redis/mysql/kafka...;
            System.out.println("save redis/mysql/kafka...");
            if (executor.getQueue().size() < 10000) {
                //TODO:try put again;
                executor.execute(r);
            }
        }
    }
}

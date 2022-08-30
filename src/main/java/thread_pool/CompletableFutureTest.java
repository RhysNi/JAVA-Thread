package thread_pool;

import utils.SleepHelper;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/30 10:22 下午
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws IOException {
        long start, end;
        start = System.currentTimeMillis();

        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(() -> queryTM());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(() -> queryJD());
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(() -> queryTB());

        //批量管理future
        CompletableFuture.allOf(futureTM, futureJD, futureTB).join();

        //lambda thenApply可以连续对返回值操作
        CompletableFuture.supplyAsync(() -> queryJD())
                .thenApply(String::valueOf)
                .thenApply(s -> "price: " + s)
                .thenAccept(System.out::println);

        end = System.currentTimeMillis();

        System.out.println("Query Success Spend:" + (end - start) + "ms");

        //阻塞操作
        System.in.read();
    }

    private static double queryTM() {
        delay();
        return 2.00;
    }

    private static double queryJD() {
        delay();
        return 4.00;
    }

    private static double queryTB() {
        delay();
        return 1.00;
    }

    private static void delay() {
        int time = new Random().nextInt(500);
        SleepHelper.sleepMilliSeconds(time);
        System.out.println("After " + time + " sleep!");
    }
}

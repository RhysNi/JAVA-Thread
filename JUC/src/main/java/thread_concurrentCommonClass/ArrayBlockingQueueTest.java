package thread_concurrentCommonClass;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/24 3:11 上午
 */
public class ArrayBlockingQueueTest {
    static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            blockingQueue.put("a" + i);
        }

        // blockingQueue.put("aaa");

        //满了`Queue full`异常
        blockingQueue.add("aaa");

        //不会报异常
        blockingQueue.offer("aaa");
        System.out.println(blockingQueue);
    }
}

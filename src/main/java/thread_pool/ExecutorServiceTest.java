package thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/30 9:40 下午
 */
public class ExecutorServiceTest {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    }
}

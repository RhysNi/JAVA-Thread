package thread_pool;

import java.util.concurrent.Executor;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/30 9:13 下午
 */
public class ExecutorTest implements Executor {

    @Override
    public void execute(Runnable command) {
        command.run();
    }

    public static void main(String[] args) {
        new ExecutorTest().execute(() -> System.out.println("Hello Executor"));
    }
}

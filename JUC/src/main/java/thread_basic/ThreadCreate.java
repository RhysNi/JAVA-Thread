package thread_basic;

import java.util.concurrent.*;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/8 11:35 下午
 */
public class ThreadCreate {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new MyThread().start();
        new Thread(new MyRunnable()).start();
        new Thread(() -> out.println("New Thread - Lambda"));

        //仅用于演示
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(() -> out.println("New Thread - threadPool"));

        //基于线程池submit
        Future<String> future = threadPool.submit(new MyCallable());
        //阻塞等待返回值
        String data1 = future.get();
        out.println(data1);
        threadPool.shutdown();

        //基于实现RunnableFuture<V>
        FutureTask<String> task = new FutureTask<String>(new MyCallable());
        //创建线程并启动
        Thread thread = new Thread(task);
        thread.start();
        //阻塞等待返回值
        String data2 = future.get();
        out.println(data2);
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            out.println("New Thread - implements Runnable");
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            out.println("New Thread - extends Thread");
        }
    }

    static class MyCallable implements Callable {
        @Override
        public String call() throws Exception {
            out.println("New Thread - implements Callable");
            return "success";
        }
    }
}

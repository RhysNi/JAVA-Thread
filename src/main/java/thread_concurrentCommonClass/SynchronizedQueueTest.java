package thread_concurrentCommonClass;

import java.util.concurrent.SynchronousQueue;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/24 3:42 上午
 */
public class SynchronizedQueueTest {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Object> synchronousQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(synchronousQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        synchronousQueue.put("aaa");
        // synchronousQueue.put("bbb");

        //不能往里面存 报Queue full异常
        // synchronousQueue.add("ccc");

        System.out.println(synchronousQueue.size());
    }
}

package thread_concurrentCommonClass;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/24 3:48 上午
 */
public class LinkedTransferQueueTest {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> transferQueue = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(transferQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        transferQueue.transfer("aaa");

        // transferQueue.put("aaa");
        // new Thread(() -> {
        //     try {
        //         System.out.println(transferQueue.take());
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        // }).start();
    }
}

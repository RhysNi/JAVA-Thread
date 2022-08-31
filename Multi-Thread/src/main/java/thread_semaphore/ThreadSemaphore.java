package thread_semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/16 4:18 上午
 */
public class ThreadSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2,true);
        new Thread(() -> {
            try {
                //将permits改为0，其他线程acquire不到
                semaphore.acquire();
                System.out.println("T1 RUNNING...");
                Thread.sleep(200);
                System.out.println("T1 RUNNING...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //将permits改为1
                semaphore.release();
            }
        }).start();

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T2 RUNNING...");
                Thread.sleep(200);
                System.out.println("T2 RUNNING...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //将permits改为1
                semaphore.release();
            }
        }).start();
    }
}

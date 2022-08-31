package thread_interview.question1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/17 12:09 上午
 */
public class ContainerWithSemaphore {
    volatile List list = new ArrayList();
    static Thread thread1 = null;
    static Thread thread2 = null;

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        ContainerWithCountDownLatch container = new ContainerWithCountDownLatch();
        Semaphore semaphore = new Semaphore(1);
        thread2 = new Thread(() -> {
            try {
                semaphore.acquire();
                out.println("thread2 end...");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1 = new Thread(() -> {
            //获取semaphore打印
            // thread1 start...
            // 0
            // 1
            // 2
            // 3
            // 4
            try {
                semaphore.acquire();
                out.println("thread1 start...");
                for (int i = 0; i < 5; i++) {
                    container.add(new Object());
                    out.println(i);
                }
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //thread1释放semaphore thread2启动获得semaphore并执行结束打印
            //thread2 end...
            try {
                thread2.start();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //thread2执行结束后释放semaphore由thread1再次获取打印
            // 5
            // 6
            // 7
            // 8
            // 9
            //执行结束释放semaphore
            try {
                semaphore.acquire();
                for (int i = 5; i < 10; i++) {
                    container.add(new Object());
                    out.println(i);
                }
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread1");
        thread1.start();
    }
}

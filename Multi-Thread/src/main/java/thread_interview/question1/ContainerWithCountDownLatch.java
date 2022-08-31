package thread_interview.question1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/17 12:09 上午
 */
public class ContainerWithCountDownLatch {
    volatile List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        ContainerWithCountDownLatch container = new ContainerWithCountDownLatch();
        //设置两道门闩
        //size!=5：thread2关上latch1，等size==5时，thread1打开latch1
        //size==5时还需要thread1关上latch2,等待thread2执行完后再由thread2打开latch2
        //thread1继续执行，否则thread2打印可能出错
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);

        new Thread(() -> {
            out.println("thread2 start...");
            if (container.size() != 5) {
                try {
                    latch1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            latch2.countDown();
            out.println("thread2 end...");
        }, "thread2").start();

        //睡一秒保证thread2先启动去监控size
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            out.println("thread1 start...");
            for (int i = 0; i < 10; i++) {
                container.add(new Object());
                out.println(i);
                if (container.size() == 5) {
                    latch1.countDown();
                    try {
                        latch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            out.println("thread1 end...");
        }, "thread1").start();
    }
}

package thread_interview.question1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/17 12:09 上午
 */
public class ContainerWithLockSupport {
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

        thread2 = new Thread(() -> {
            out.println("thread2 start...");
            if (container.size() != 5) {
                LockSupport.park();
            }
            LockSupport.unpark(thread1);
            out.println("thread2 end...");
        });
        thread2.start();

        //睡一秒保证thread2先启动去监控size
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1 = new Thread(() -> {
            out.println("thread1 start...");
            for (int i = 0; i < 10; i++) {
                container.add(new Object());
                out.println(i);
                if (container.size() == 5) {
                    LockSupport.unpark(thread2);
                    LockSupport.park();
                }
            }
            out.println("thread1 end...");
        }, "thread1");
        thread1.start();
    }
}

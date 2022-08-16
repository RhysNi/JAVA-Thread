package thread_interview.question1;

import utils.SleepHelper;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/17 12:09 上午
 */
public class ContainerWithWaitAndNotify {
    volatile List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        ContainerWithWaitAndNotify container = new ContainerWithWaitAndNotify();

        final Object lock = new Object();

        new Thread(() -> {
            out.println("thread2 start...");
            synchronized (lock) {
                if (container.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                out.println("thread2 end...");
                //确保thread2结束后thread1能继续执行
                lock.notify();
            }
        }, "thread2").start();

        //睡一秒保证thread2先启动去监控size
        SleepHelper.sleepSeconds(1);

        new Thread(() -> {
            out.println("thread1 start...");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    container.add(new Object());
                    out.println(i);
                    if (container.size() == 5) {
                        lock.notify();
                        try {
                            //由于notify不释放锁，所以notify后需要自己wait从而把锁释放出来
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    SleepHelper.sleepSeconds(1);
                }
                out.println("thread1 end...");
            }
        }, "thread1").start();
    }
}

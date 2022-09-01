package thread_interview.question2;

import utils.SleepHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/17 12:09 上午
 */
public class ContainerWithSynchronized<T> {
    final private LinkedList<T> list = new LinkedList<T>();
    final private int MAX = 10;
    private int count = 0;

    public synchronized void put(T t) {
        if (list.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        //synchronized保证count成功加1，否则会出现没完成加1就被另一个线程读过去了，那另一个线程拿到的还是老值，可能会导致计数出错
        ++count;
        this.notifyAll();
    }

    public synchronized T get() {
        T t = null;
        //如果用`if`：当wait被唤醒则会直接往下执行，不会再次判断list.size() == 0 因此用 while
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        ContainerWithSynchronized<String> container = new ContainerWithSynchronized<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    out.println(Thread.currentThread().getName() + " 吃到了由" + container.get());
                }
            }, "consumer" + i).start();
        }

        SleepHelper.sleepSeconds(1);

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    container.put(Thread.currentThread().getName() + "生产的 " + j + "号包子");
                }
            }, "producer" + i).start();
        }
    }
}

package thread_interview.question2;

import utils.SleepHelper;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/17 12:09 上午
 */
public class ContainerWithRenntrantLock<T> {
    final private LinkedList<T> list = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    //Condition ： producer等待队列
    private Condition producer = lock.newCondition();
    //Condition ： consumer等待队列
    private Condition consumer = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            while (list.size() == MAX) {
                producer.await();
            }
            list.add(t);
            //synchronized保证count成功加1，否则会出现没完成加1就被另一个线程读过去了，那另一个线程拿到的还是老值，可能会导致计数出错
            ++count;
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while (list.size() == 0) {
                consumer.await();
            }
            t = list.removeFirst();
            count--;
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        ContainerWithRenntrantLock<String> container = new ContainerWithRenntrantLock<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    out.println(Thread.currentThread().getName() + " 吃到了由" + container.get());
                }
            }, "consumer" + i).start();
        }

        SleepHelper.sleepSeconds(2);

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    container.put(Thread.currentThread().getName() + "生产的 " + j + "号包子");
                }
            }, "provider" + i).start();
        }
    }
}

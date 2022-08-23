package thread_concurrentCommonClass;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/24 3:23 上午
 */
public class DelayQueueTest {
    static BlockingQueue<MyTask> blockingQueue = new DelayQueue<MyTask>();
    static Random random = new Random();

    private static class MyTask implements Delayed {
        String name;
        long runningTime;

        public MyTask(String name, long runningTime) {
            this.name = name;
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return name + " " + runningTime;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        MyTask t1 = new MyTask("t1", start + 1000);
        MyTask t2 = new MyTask("t2", start + 2000);
        MyTask t3 = new MyTask("t3", start + 1500);
        MyTask t4 = new MyTask("t4", start + 2500);
        MyTask t5 = new MyTask("t5", start + 500);

        blockingQueue.put(t1);
        blockingQueue.put(t2);
        blockingQueue.put(t3);
        blockingQueue.put(t4);
        blockingQueue.put(t5);

        System.out.println(blockingQueue);

        for (int i = 0; i < 5; i++) {
            System.out.println(blockingQueue.take());
        }
    }
}

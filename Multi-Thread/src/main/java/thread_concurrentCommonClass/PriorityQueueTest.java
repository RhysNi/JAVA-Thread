package thread_concurrentCommonClass;

import java.util.PriorityQueue;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/24 3:37 上午
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<Object> priorityQueue = new PriorityQueue<>();
        priorityQueue.add("a");
        priorityQueue.add("g");
        priorityQueue.add("s");
        priorityQueue.add("v");
        priorityQueue.add("r");

        for (int i = 0; i < 5; i++) {
            System.out.println(priorityQueue.poll());
        }
    }
}

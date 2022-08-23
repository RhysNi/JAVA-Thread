package thread_concurrentCommonClass;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/24 2:53 上午
 */
public class ConcurrentQueueTest {
    public static void main(String[] args) {
        Queue<String> linkedQueue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            //代替Add,原来Add会抛异常，offer是boolean值
            linkedQueue.offer("a" + i);
        }

        System.out.println(linkedQueue);
        System.out.println(linkedQueue.size());
        //poll取并删除
        System.out.println(linkedQueue.poll());
        System.out.println(linkedQueue.size());
        //peek:取不删除
        System.out.println(linkedQueue.peek());
        System.out.println(linkedQueue.size());
    }
}

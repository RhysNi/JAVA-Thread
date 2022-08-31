package thread_RefTypeAndThreadLocal;

import utils.SleepHelper;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/19 1:51 上午
 */
public class PhantomReferenceTest {
    public static final List<Object> LIST = new LinkedList<>();
    public static final ReferenceQueue<OFinalize> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
        PhantomReference<OFinalize> phantomReference = new PhantomReference<OFinalize>(new OFinalize(), QUEUE);
        new Thread(() -> {
            while (true) {
                LIST.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(phantomReference.get());
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference<? extends OFinalize> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("--- 虚引用对象被jvm回收了 ----" + poll);
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

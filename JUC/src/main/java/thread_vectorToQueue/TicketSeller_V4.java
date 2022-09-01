package thread_vectorToQueue;

import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/24 12:39 上午
 */
public class TicketSeller_V4 {
    static ConcurrentLinkedQueue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String s = tickets.poll();
                    if (s == null) {
                        break;
                    }
                    System.out.println("售出：" + s);
                }
            }).start();
        }
    }
}

package thread_vectorToQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/24 12:39 上午
 */
public class TicketSeller_V1 {
    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    System.out.println("售出：" + tickets.remove(0));
                }
            }).start();
        }
    }
}

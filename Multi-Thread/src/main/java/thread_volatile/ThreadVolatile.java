package thread_volatile;

import utils.SleepHelper;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/9 3:03 上午
 */
public class ThreadVolatile {
    private static volatile boolean running = true;

    public static void main(String[] args) {
        new Thread(ThreadVolatile::test, "thread1").start();
        SleepHelper.sleepSeconds(1);
        running = false;
    }

    private static void test() {
        System.out.println("test start...");
        while (running) {
            // System.out.println("Hello Volatile");
        }
        System.out.println("test end...");
    }
}

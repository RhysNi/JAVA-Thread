package thread_volatile;

import utils.SleepHelper;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/9 3:03 上午
 */
public class ThreadVolatileReference {
    private volatile static TestClass t = new TestClass();

    public static void main(String[] args) {
        new Thread(t::testMethod, "thread1").start();
        SleepHelper.sleepSeconds(1);
        t.running = false;
    }

    private static class TestClass {
        boolean running = true;

        void testMethod() {
            out.println("test start...");
            while (running) {
            }
            out.println("test end...");
        }
    }
}

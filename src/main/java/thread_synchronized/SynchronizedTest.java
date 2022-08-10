package thread_synchronized;


import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/10 11:47 下午
 */
public class SynchronizedTest {
    synchronized void method1() {
        out.println(Thread.currentThread().getName() + " method1 start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.println(Thread.currentThread().getName() + " method1 end...");
    }

    void method2() {
        out.println(Thread.currentThread().getName() + " method2 start...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.println(Thread.currentThread().getName() + " method2 end...");
    }

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        new Thread(test::method1, "thread1").start();
        new Thread(test::method2, "thread2").start();
    }
}

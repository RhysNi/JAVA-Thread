package thread_ThreadLocal;

import utils.SleepHelper;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/18 9:52 下午
 */
public class ThreadLocalTest {
    static ThreadLocal<Person> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            SleepHelper.sleepSeconds(2);
            System.out.println(threadLocal.get());
        }).start();

        new Thread(() -> {
            SleepHelper.sleepSeconds(1);
            threadLocal.set(new Person());
        }).start();
    }
}

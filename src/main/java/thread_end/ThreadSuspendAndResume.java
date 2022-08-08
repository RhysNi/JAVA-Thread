package thread_end;

import utils.SleepHelper;

import static java.lang.System.out;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/9 2:34 上午
 */
public class ThreadSuspendAndResume {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                out.println("Go On...");
                SleepHelper.sleepSeconds(1);
            }
        });

        thread.start();
        SleepHelper.sleepSeconds(5);
        //5秒后暂停
        //如果暂停的时候持有一把没被释放的锁，如果忘记重新继续那这把锁永远不会被释放继而产生死锁问题
        thread.suspend();
        SleepHelper.sleepSeconds(3);
        //暂停3秒后继续
        thread.resume();
    }
}

package thread_LockSupport;

import utils.SleepHelper;

import java.util.concurrent.locks.LockSupport;

import static java.lang.System.*;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/16 11:50 下午
 */
public class ThreadLockSupport {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                out.println(i);
                if (i == 5) {
                    //当前线程阻塞停止
                    LockSupport.park();
                }
                SleepHelper.sleepSeconds(1);
            }
        });
        thread.start();
        SleepHelper.sleepSeconds(8);
        out.println("after 8 seconds");
        LockSupport.unpark(thread);
    }
}

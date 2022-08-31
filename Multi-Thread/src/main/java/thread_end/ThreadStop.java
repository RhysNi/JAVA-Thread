package thread_end;

import utils.SleepHelper;

import static java.lang.System.*;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/9 2:34 上午
 */
public class ThreadStop {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                out.println("Go On...");
                SleepHelper.sleepSeconds(1);
            }
        });

        thread.start();
        SleepHelper.sleepSeconds(5);

        //被废弃的方法，不建议使用
        //不管线程当前处于什么状态，直接干掉，非常容易产生数据不一致问题
        thread.stop();
    }
}

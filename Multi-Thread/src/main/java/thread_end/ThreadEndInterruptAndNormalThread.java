package thread_end;

import utils.SleepHelper;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/9 2:45 上午
 */
public class ThreadEndInterruptAndNormalThread {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                //sleep、wait的处理也可以正确结束
            }
            System.out.println("thread end");
        });

        thread.start();
        SleepHelper.sleepSeconds(1);

        thread.interrupt();
    }
}

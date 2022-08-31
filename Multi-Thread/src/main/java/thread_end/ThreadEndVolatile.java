package thread_end;

import utils.SleepHelper;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/9 2:45 上午
 */
public class ThreadEndVolatile {
    public static volatile boolean running = true;

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            long i = 0L;
            while (running) {
                i++;
            }

            //end -- i = 3957503196
            //end -- i = 3512231997
            //不能控制具体多长时间能够停止
            System.out.println("end -- i = " + i);
        });

        thread.start();
        SleepHelper.sleepSeconds(1);

        running = false;
    }
}

package utils;

import java.util.concurrent.TimeUnit;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/9 12:16 上午
 */
public class SleepHelper {
    public static void sleepSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleepMilliSeconds(int milliSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

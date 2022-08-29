package thread_interview.question3;

import utils.SleepHelper;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/26 2:52 上午
 */
public class UseLockSupport {
    static Thread thread1 = null;
    static Thread thread2 = null;

    public static void main(String[] args) {
        char[] intArr = "1234567".toCharArray();
        char[] charArr = "ABCDEFG".toCharArray();

        thread1 = new Thread(() -> {
            for (char c : charArr) {
                System.out.print(c);
                //打印完字母唤醒thread2打印数字
                LockSupport.unpark(thread2);
                //随即当前线程阻塞
                LockSupport.park();
            }
        }, "thread1");

        thread2 = new Thread(() -> {
            for (char i : intArr) {
                //当前thread2启动立即阻塞等待thread1打印完字母后被唤醒，否则可能先打印数字造成打印结果乱序
                LockSupport.park();
                System.out.println(i);
                //打印完数字继续唤醒thread1打印字母
                LockSupport.unpark(thread1);
            }
        }, "thread2");

        thread1.start();
        thread2.start();
    }
}

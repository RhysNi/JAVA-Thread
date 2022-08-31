package thread_interview.question3;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/26 2:52 上午
 */
public class UseCas {
    enum RunEnum {THREAD1, THREAD2}

    static volatile RunEnum run = RunEnum.THREAD1;

    public static void main(String[] args) {
        char[] intArr = "1234567".toCharArray();
        char[] charArr = "ABCDEFG".toCharArray();


        new Thread(() -> {
            for (char c : charArr) {
                //自旋等待run变为THREAD1
                while (run != RunEnum.THREAD1) {
                }
                System.out.print(c);
                //thread1执行完变为THREAD2，让thread2继续打印
                run = RunEnum.THREAD2;
            }
        }, "thread1").start();

        new Thread(() -> {
            for (char i : intArr) {
                //自旋等待run变为THREAD2
                while (run != RunEnum.THREAD2) {
                }
                System.out.print(i);
                //thread2执行完变为THREAD1，让thread1继续打印
                run = RunEnum.THREAD1;
            }
        }, "thread2").start();
    }
}

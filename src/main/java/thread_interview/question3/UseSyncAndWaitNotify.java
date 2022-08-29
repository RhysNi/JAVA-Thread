package thread_interview.question3;


/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/26 2:52 上午
 */
public class UseSyncAndWaitNotify {
    //采用标志让其中一个线程自旋不占用锁，以保证另一个线程先行启动能正常获取锁
    private static volatile boolean THREAD_FLAG = false;

    public static void main(String[] args) {
        final Object o = new Object();

        char[] intArr = "1234567".toCharArray();
        char[] charArr = "ABCDEFG".toCharArray();

        new Thread(() -> {
            synchronized (o) {
                for (char c : charArr) {
                    System.out.print(c);
                    //thread1启动打印后将标志设为true（如果thread2线程先行启动的情况下，这时就代表thread2自旋结束可以正常打印）
                    THREAD_FLAG = true;
                    try {
                        //唤醒其他线程
                        o.notify();
                        //阻塞释放锁
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //循环结束必须再次调用notify让程序停止
                o.notify();
            }
        }, "thread1").start();

        new Thread(() -> {
            synchronized (o) {
                //自旋保证thread1没启动时当前线程不占用锁
                while (!THREAD_FLAG) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (char i : intArr) {
                    System.out.print(i);
                    try {
                        //唤醒其他线程
                        o.notify();
                        //阻塞释放锁
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //循环结束必须再次调用notify让程序停止
                o.notify();
            }
        }, "thread2").start();
    }
}

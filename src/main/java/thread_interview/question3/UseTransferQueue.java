package thread_interview.question3;


import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/26 2:52 上午
 */
public class UseTransferQueue {
    static TransferQueue<Character> queue = new LinkedTransferQueue<>();

    public static void main(String[] args) {
        char[] intArr = "1234567".toCharArray();
        char[] charArr = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (char c : charArr) {
                try {
                    //把thread1的`c`放到队列中供thread2中的queue.take
                    queue.transfer(c);
                    //随即再将thread1中queue进入take等待thread2放入数字
                    System.out.print(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread1").start();

        new Thread(() -> {
            for (char i : intArr) {
                try {
                    //take thread1放入的字母元素
                    System.out.print(queue.take());
                    //随即再将thread2的数字元素放进队列交由thread1去take打印
                    queue.transfer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread2").start();
    }
}

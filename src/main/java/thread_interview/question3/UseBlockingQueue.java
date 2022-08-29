package thread_interview.question3;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/26 2:52 上午
 */
public class UseBlockingQueue {
    static BlockingQueue<String> queue1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> queue2 = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        char[] intArr = "1234567".toCharArray();
        char[] charArr = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (char c : charArr) {
                System.out.print(c);
                try {
                    //打印完往队列塞一个success
                    queue1.put("success");
                    //让queue2进入take状态，队列没有可消费的就会阻塞等待
                    queue2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread1").start();

        new Thread(() -> {
            for (char i : intArr) {
                try {
                    //先看queue1中有没有可消费的，如果没有则阻塞等待
                    queue1.take();
                    //消费成功则打印
                    System.out.print(i);
                    //打印完再往queue2中扔一个消息让queue2 take到继续打印thread1中的字母
                    queue2.put("success");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread2").start();
    }
}

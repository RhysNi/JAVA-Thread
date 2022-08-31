package thread_interview.question3;


import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/26 2:52 上午
 */
public class UsePipedStream {

    public static void main(String[] args) throws IOException {
        char[] intArr = "1234567".toCharArray();
        char[] charArr = "ABCDEFG".toCharArray();

        PipedInputStream inputStream1 = new PipedInputStream();
        PipedInputStream inputStream2 = new PipedInputStream();

        PipedOutputStream outputStream1 = new PipedOutputStream();
        PipedOutputStream outputStream2 = new PipedOutputStream();

        //in <-> out 两两建立通信
        inputStream1.connect(outputStream2);
        inputStream2.connect(outputStream1);

        String msg = "AAA BBB";
        new Thread(() -> {
            try {
                byte[] buffer = new byte[7];
                for (char c : charArr) {
                    System.out.print(c);
                    //outputStream1写出给inputStream2接受
                    outputStream1.write(msg.getBytes(StandardCharsets.UTF_8));
                    //读取outputStream2的内容到buffer
                    inputStream1.read(buffer);
                    //如果相等则跳过本次循环继续打印下一个字母
                    if (msg.equals(new String(buffer))) {
                        continue;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "thread1").start();

        new Thread(() -> {
            try {
                byte[] buffer = new byte[7];
                for (char i : intArr) {
                    //inputStream2接受outputStream1的内容
                    inputStream2.read(buffer);
                    //如果相等就打印数字
                    if (msg.equals(new String(buffer))) {
                        System.out.print(i);
                    }
                    //打印完再次给inputStream1写出msg
                    outputStream2.write(msg.getBytes(StandardCharsets.UTF_8));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "thread2").start();
    }
}

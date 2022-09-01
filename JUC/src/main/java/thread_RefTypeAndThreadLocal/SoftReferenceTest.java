package thread_RefTypeAndThreadLocal;

import utils.SleepHelper;

import java.lang.ref.SoftReference;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/19 12:45 上午
 */
public class SoftReferenceTest {
    public static void main(String[] args) {
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(softReference.get());
        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(softReference.get());
        byte[] bytes = new byte[1024 * 1024 * 10];

        System.out.println(softReference.get());
    }
}

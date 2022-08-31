package thread_RefTypeAndThreadLocal;

import java.lang.ref.WeakReference;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/19 12:45 上午
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        WeakReference<OFinalize> weakReference = new WeakReference<>(new OFinalize());
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());

        ThreadLocal<OFinalize> threadLocal = new ThreadLocal<>();
        threadLocal.set(new OFinalize());
        //ThreadLocal用完即remove
        threadLocal.remove();
    }
}

package thread_RefTypeAndThreadLocal;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/19 12:38 上午
 */
public class OFinalize {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}

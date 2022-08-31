package thread_RefTypeAndThreadLocal;

import java.io.IOException;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/19 12:39 上午
 */
public class NormalReference {
    public static void main(String[] args) throws IOException {
        OFinalize oFinalize = new OFinalize();
        oFinalize = null;
        System.gc();
        System.in.read();
    }
}

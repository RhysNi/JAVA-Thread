package thread_interview.question1;

import utils.SleepHelper;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/17 12:09 上午
 */
public class ContainerWithVolatile {
    volatile List list = new ArrayList();

    //也可使用synchronizedList同步容器
    // volatile List list = Collections.synchronizedList(new ArrayList<>());

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        ContainerWithVolatile container = new ContainerWithVolatile();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                container.add(new Object());
                out.println(i);
                SleepHelper.sleepSeconds(1);
            }
            out.println("thread1 add success...");
        }, "thread1").start();

        new Thread(() -> {
            while (true) {
                if (container.size() == 5) {
                    break;
                }
            }
            out.println("thread2 end...");
        }, "thread2").start();
    }
}

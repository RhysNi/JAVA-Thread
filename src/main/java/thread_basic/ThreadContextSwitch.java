package thread_basic;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/8 11:18 下午
 */
public class ThreadContextSwitch {
    public static double[] nums = new double[1_0000_0000];
    public static Random random = new Random();
    public static DecimalFormat df = new DecimalFormat("0.00");

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextDouble();
        }
    }

    //TODO:待完善
}

package thread_phaser;

import java.util.concurrent.Phaser;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/16 3:21 上午
 */
public class MarriagePhaser extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case 0:
                System.out.println("所有人到齐了~" + registeredParties);
                System.out.println();
                return false;
            case 1:
                System.out.println("所有人吃完了~" + registeredParties);
                System.out.println();
                return false;
            case 2:
                System.out.println("所有人离开了了~" + registeredParties);
                System.out.println();
                return false;
            case 3:
                System.out.println("婚礼结束~" + registeredParties);
                System.out.println();
                return true;
        }
        return false;
    }
}

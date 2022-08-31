package thread_phaser;

import java.util.Random;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/16 3:14 上午
 */
public class ThreadPhaser {
    static MarriagePhaser phaser = new MarriagePhaser();

    public static void main(String[] args) {
        phaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("p" + i, phaser)).start();
        }
        new Thread(new Person("新郎",phaser)).start();
        new Thread(new Person("新娘",phaser)).start();
    }
}

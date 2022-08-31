package thread_phaser;

import utils.SleepHelper;

import java.util.Random;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/16 3:25 上午
 */
public class Person implements Runnable {
    static Random random = new Random();

    private String name;
    private MarriagePhaser phaser;

    public Person(String name, MarriagePhaser phaser) {
        this.name = name;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        arrive();
        eat();
        leave();
        hug();
    }

    public void arrive() {
        SleepHelper.sleepSeconds(random.nextInt(10));
        System.out.println(name + "到达现场~");
        phaser.arriveAndAwaitAdvance();
    }

    public void eat() {
        SleepHelper.sleepSeconds(random.nextInt(10));
        System.out.println(name + "吃完~");
        phaser.arriveAndAwaitAdvance();
    }

    public void leave() {
        SleepHelper.sleepSeconds(random.nextInt(10));
        System.out.println(name + "离开~");
        phaser.arriveAndAwaitAdvance();
    }

    public void hug() {
        if ("新郎".equals(name) || "新娘".equals(name)) {
            SleepHelper.sleepSeconds(random.nextInt(10));
            System.out.println(name + "入洞房~");
            phaser.arriveAndAwaitAdvance();
        } else {
            phaser.arriveAndDeregister();
        }
    }
}

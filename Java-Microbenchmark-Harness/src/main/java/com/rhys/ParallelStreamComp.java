package com.rhys;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/9/1 2:14 上午
 */
public class ParallelStreamComp {
    static List<Integer> nums = new ArrayList<>();

    static {
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            nums.add(1000000 + r.nextInt(1000000));
        }
    }

    static void foreach() {
        nums.forEach(v -> isPrime(v));
    }

    static void parallel() {
        nums.parallelStream().forEach(ParallelStreamComp::isPrime);
    }

    static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

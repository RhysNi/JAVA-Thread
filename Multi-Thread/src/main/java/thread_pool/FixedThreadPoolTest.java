package thread_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/30 11:56 下午
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        //利用主线程单独运算
        getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        //计算密集型，CPU利用100%
        final int cpuCoreNum = 8;
        ExecutorService executor = Executors.newFixedThreadPool(cpuCoreNum);

        //创建8个任务分别提交并行计算
        MyTask task1 = new MyTask(1, 25000);
        MyTask task2 = new MyTask(25001, 50000);
        MyTask task3 = new MyTask(50001, 75000);
        MyTask task4 = new MyTask(75001, 100000);
        MyTask task5 = new MyTask(100001, 125000);
        MyTask task6 = new MyTask(125001, 150000);
        MyTask task7 = new MyTask(150001, 175000);
        MyTask task8 = new MyTask(175001, 200000);

        Future<List<Integer>> future1 = executor.submit(task1);
        Future<List<Integer>> future2 = executor.submit(task2);
        Future<List<Integer>> future3 = executor.submit(task3);
        Future<List<Integer>> future4 = executor.submit(task4);
        Future<List<Integer>> future5 = executor.submit(task5);
        Future<List<Integer>> future6 = executor.submit(task6);
        Future<List<Integer>> future7 = executor.submit(task7);
        Future<List<Integer>> future8 = executor.submit(task8);

        start = System.currentTimeMillis();
        future1.get();
        future2.get();
        future3.get();
        future4.get();
        future5.get();
        future6.get();
        future7.get();
        future8.get();
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        MyTask(int s, int e) {
            this.startPos = s;
            this.endPos = e;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> r = getPrime(startPos, endPos);
            return r;
        }
    }

    /**
     * 获取质数列表
     *
     * @param start 开始索引
     * @param end   结束索引
     * @return java.util.List<java.lang.Integer>
     * @author Rhys.Ni
     * @date 2022/8/31
     */
    static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                results.add(i);
            }
        }
        return results;
    }

    /**
     * 判断质数
     *
     * @param num 数字
     * @return boolean
     * @author Rhys.Ni
     * @date 2022/8/31
     */
    static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

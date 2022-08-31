package thread_pool;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/31 3:39 上午
 */
public class ForkJoinPoolTest {
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }

        System.out.println("---" + Arrays.stream(nums).sum());
    }

    //RecursiveAction extends ForkJoinTask<Void> 不带返回值
    static class AddTask extends RecursiveAction {
        int start, end;

        AddTask(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        protected void compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println("from:" + start + " to:" + end + " = " + sum);
            } else {
                //超出最大任务数，将任务一分为二变成两个任务
                int middle = start + (end - start) / 2;
                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                subTask1.fork();
                subTask2.fork();
            }
        }
    }


    //RecursiveTask<V> extends ForkJoinTask<V> 带返回值
    static class AddTaskRet extends RecursiveTask<Long> {

        private static final long serialVersionUID = 1L;
        int start, end;

        AddTaskRet(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            }
            //切分任务
            int middle = start + (end - start) / 2;
            AddTaskRet subTask1 = new AddTaskRet(start, middle);
            AddTaskRet subTask2 = new AddTaskRet(middle, end);
            subTask1.fork();
            subTask2.fork();
            return subTask1.join() + subTask2.join();
        }

    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        AddTask task = new AddTask(0, nums.length);
        pool.execute(task);

        //-----------------------------------------------------

        AddTaskRet taskRet = new AddTaskRet(0, nums.length);
        pool.execute(taskRet);
        long result = taskRet.join();
        System.out.println(result);
    }
}

package cn.someget.demo;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 常规线程池遇到的问题demo
 *
 * @author zyf
 * @date 2022-03-13 13:03
 */
@Slf4j
@SuppressWarnings("all")
public class ThreadPoolExecutorDemo {


    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4,
                4,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000),
//                new SynchronousQueue<>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        NormalTask task1 = new NormalTask("task1", 1, pool);
        NormalTask task2 = new NormalTask("task2", 1, pool);
        NormalTask task3 = new NormalTask("task3", 1, pool);
        NormalTask task4 = new NormalTask("task4", 1, pool);
        NormalTask task5 = new NormalTask("task5", 5, pool);
        NormalTask task6 = new NormalTask("task6", 5, pool);
        NormalTask task7 = new NormalTask("task7", 1, pool);
        NormalTask task8 = new NormalTask("task8", 1, pool);
        NormalTask task9 = new NormalTask("task9", 1, pool);

        task2.getDependentTasks().add(task7);
        task3.getDependentTasks().add(task8);
        task4.getDependentTasks().add(task9);
        task1.getDependentTasks().addAll(Lists.newArrayList(task2, task3, task4, task5, task6));
        log.debug("ThreadPoolExecutorDemo starting");
        pool.submit(task1);
    }
}
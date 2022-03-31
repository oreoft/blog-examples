package cn.someget.demo;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.concurrent.ForkJoinPool;

/**
 * fj的demo
 *
 * @author zyf
 * @date 2022-03-13 13:03
 */
@Slf4j
@SuppressWarnings("all")
public class ForkJoinPoolDemo {


    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);

        FjTask task1 = new FjTask("task1", 1);
        FjTask task2 = new FjTask("task2", 1);
        FjTask task3 = new FjTask("task3", 1);
        FjTask task4 = new FjTask("task4", 1);
        FjTask task5 = new FjTask("task5", 5);
        FjTask task6 = new FjTask("task6", 5);
        FjTask task7 = new FjTask("task7", 1);
        FjTask task8 = new FjTask("task8", 1);
        FjTask task9 = new FjTask("task9", 1);

        task2.getDependentTasks().add(task7);
        task3.getDependentTasks().add(task8);
        task4.getDependentTasks().add(task9);
        task1.getDependentTasks().addAll(Lists.newArrayList(task2, task3, task4, task5, task6));
        log.debug("ForkJoinPoolDemo starting");
        pool.invoke(task1);
    }
}
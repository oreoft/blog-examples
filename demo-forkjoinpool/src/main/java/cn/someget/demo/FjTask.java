package cn.someget.demo;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * forkjoinpool的任务
 *
 * @author zyf
 * @date 2022-03-20 23:03
 */
@Data
@Slf4j
public class FjTask extends RecursiveAction {

    /**
     * 任务名称
     */
    private final String taskName;

    /**
     * 任务耗时(单位s)
     */
    private final int taskCost;
    /**
     * 子任务
     */
    private List<FjTask> dependentTasks;

    public FjTask(String taskName, int taskCost) {
        this.taskName = taskName;
        this.taskCost = taskCost;
        this.dependentTasks = Lists.newArrayList();
    }

    @Override
    @SneakyThrows
    protected void compute() {
        for (FjTask dependentTask : dependentTasks) {
            dependentTask.fork();
        }
        for (FjTask dependentTask : dependentTasks) {
            dependentTask.join();
        }
        TimeUnit.SECONDS.sleep(taskCost);
        log.debug("taskName:{}", taskName);
    }
}

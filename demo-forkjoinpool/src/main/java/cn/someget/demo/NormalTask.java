package cn.someget.demo;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Data
class NormalTask implements Runnable {
    /**
     * 任务名称
     */
    private final String taskName;
    /**
     * 子任务所用的线程池
     */
    private final ThreadPoolExecutor pool;
    /**
     * 任务耗时
     */
    private final int taskCost;
    /**
     * 子任务
     */
    private List<NormalTask> dependentTasks;

    public NormalTask(String taskName, int taskCost, ThreadPoolExecutor pool) {
        this.taskName = taskName;
        this.pool = pool;
        this.taskCost = taskCost;
        this.dependentTasks = Lists.newArrayList();
    }

    @Override
    @SneakyThrows
    public void run() {
        // 先提交子任务
        List<CompletableFuture<Void>> result = dependentTasks.stream().map(task -> CompletableFuture.runAsync(task, pool))
                .collect(Collectors.toList());
        for (CompletableFuture<Void> completableFuture : result) {
            completableFuture.get();
        }
        // 模拟当前线程任务
        TimeUnit.SECONDS.sleep(taskCost);
        log.debug("taskName:{}", taskName);
    }
}
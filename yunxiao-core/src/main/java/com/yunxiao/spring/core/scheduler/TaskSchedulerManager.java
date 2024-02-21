package com.yunxiao.spring.core.scheduler;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.CronTask;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

/**
 * @author LuoYunXiao
 * @since 2023/10/17 23:48
 */
@Slf4j
public class TaskSchedulerManager implements DisposableBean, InitializingBean {

    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private static final ConcurrentHashMap<String, ScheduledFuture<?>> tasks = new ConcurrentHashMap<>();

    public TaskSchedulerManager(ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    /**
     * 添加任务到调度器，如果任务存在相同Id,执行任务覆盖
     *
     * @param taskId   任务唯一Id
     * @param cronTask 任务
     */
    public void schedule(@NonNull String taskId, @NonNull CronTask cronTask) {
        cancel(taskId);
        ScheduledFuture<?> scheduledFuture = threadPoolTaskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
        if (scheduledFuture != null) {
            tasks.put(taskId, scheduledFuture);
            return;
        }
        log.error("任务调度失败,taskId:{}", taskId);
    }

    public void cancel(String taskId) {
        ScheduledFuture<?> removed = tasks.remove(taskId);
        if (removed != null) {
            removed.cancel(true);
        }
    }


    @Override
    public void destroy() {
        threadPoolTaskScheduler.destroy();
        tasks.values().forEach(scheduledFuture -> scheduledFuture.cancel(true));
        tasks.clear();
    }

    @Override
    public void afterPropertiesSet() {
        if (log.isDebugEnabled()) {
            Logger logger = (Logger) LoggerFactory.getLogger(ThreadPoolTaskScheduler.class);
            logger.setLevel(Level.DEBUG);
        }
        threadPoolTaskScheduler.initialize();
    }

}

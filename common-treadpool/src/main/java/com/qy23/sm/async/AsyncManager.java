package com.qy23.sm.async;

import com.qy23.sm.spring.SpringUtils;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncManager {

    private AsyncManager() {

    }

    //拿到线程池
    private ScheduledThreadPoolExecutor executor = SpringUtils.getBean(ScheduledThreadPoolExecutor.class);

    //设为单例模式
    private static AsyncManager asyncManager = new AsyncManager();

    public static AsyncManager getInstance() {

        return asyncManager;
    }

    //运行线程池
    public void executeTask(Runnable runnable) {
        executor.schedule(runnable, 5, TimeUnit.SECONDS);
    }

    //关闭线程池
    public void close() {
        if (!executor.isShutdown()) {
            executor.shutdownNow();
        }
    }
}

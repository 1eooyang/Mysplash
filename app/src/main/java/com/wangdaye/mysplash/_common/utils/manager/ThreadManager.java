package com.wangdaye.mysplash._common.utils.manager;

import com.wangdaye.mysplash._common.utils.widget.runnable.PriorityRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Thread manager.
 * */

public class ThreadManager {
    private ExecutorService threadPool;

    /** <br> life cycle. */

    private ThreadManager() {
        int coreCount = Runtime.getRuntime().availableProcessors();
        this.threadPool = new ThreadPoolExecutor(
                coreCount, coreCount * 2,
                0, TimeUnit.SECONDS,
                new PriorityBlockingQueue<Runnable>());
    }

    /** <br> data. */

    public void execute(PriorityRunnable runnable) {
        threadPool.execute(runnable);
    }

    /** <br> singleton. */

    private static ThreadManager instance;

    public static ThreadManager getInstance() {
        synchronized (ThreadManager.class) {
            if (instance == null) {
                instance = new ThreadManager();
            }
        }
        return instance;
    }
}

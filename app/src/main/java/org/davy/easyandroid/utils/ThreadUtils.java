package org.davy.easyandroid.utils;

import android.support.annotation.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadUtils {

    private static final RejectedExecutionHandler defaultHandler =
            new ThreadPoolExecutor.AbortPolicy();

    /**
     * The default thread factory.
     */
    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "easy-android-single-pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    private static ThreadFactory defaultThreadFactory() {
        return new DefaultThreadFactory();
    }

    private static ThreadUtils instance;

    public static ThreadUtils getInstance() {
        if (instance == null) {
            synchronized (ThreadUtils.class) {
                instance = new ThreadUtils();
            }
        }
        return instance;
    }

    private ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(), defaultThreadFactory(), defaultHandler);

    private ScheduledExecutorService mScheduledExecutorService = Executors.newScheduledThreadPool(1);

    private ThreadUtils() { }

    public void addSingleTask() {
        mThreadPoolExecutor.allowCoreThreadTimeOut(true);
        mThreadPoolExecutor.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("addSingleTask");
                return null;
            }
        });
    }

    public void addScheduledTask(@NonNull final Runnable task) {
        mScheduledExecutorService.schedule(task, 1000L, TimeUnit.SECONDS);
    }

    public void addScheduledTask(@NonNull final Callable task) {
        mScheduledExecutorService.schedule(task, 1000L, TimeUnit.SECONDS);
    }

    public void addScheduledTaskAtFixedRate(@NonNull final Runnable task) {
        mScheduledExecutorService.scheduleAtFixedRate(task, 1000L, 1000L, TimeUnit.SECONDS);
    }

    public void addScheduledTaskWithFixedDelay(@NonNull final Runnable task) {
        mScheduledExecutorService.scheduleWithFixedDelay(task, 1000L,1000L, TimeUnit.SECONDS);
    }
}

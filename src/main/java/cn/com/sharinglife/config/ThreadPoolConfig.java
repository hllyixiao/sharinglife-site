package cn.com.sharinglife.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义线程池
 * Created by hell on 2018/2/6
 *
 * @author hell
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig implements AsyncConfigurer {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * 是否启用自定义的线程池，否则启用默认的线程池
     */
    @Value("${my.thread.pool.enable:false}")
    private boolean enable;
    /**
     * 线程池维护线程的最少数量
     */
    @Value("${my.thread.pool.corePoolSize:5}")
    private int corePoolSize;
    /**
     * 线程池维护线程的最大数量
     */
    @Value("${my.thread.pool.maxPoolSize:10}")
    private int maxPoolSize;
    /**
     * 缓存队列大小
     */
    @Value("${my.thread.pool.queueCapacity:2}")
    private int queueCapacity;
    /**
     * 允许的空闲时间
     */
    @Value("${my.thread.pool.keepAlive:60}")
    private int keepAlive;

    @Override
    public Executor getAsyncExecutor() {
        if (enable) {
            ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
            //当前线程数
            threadPool.setCorePoolSize(corePoolSize);
            // 最大线程数
            threadPool.setMaxPoolSize(maxPoolSize);
            //线程池所使用的缓冲队列
            threadPool.setQueueCapacity(queueCapacity);
            //threadPool.setWaitForTasksToCompleteOnShutdown(true);//等待任务在关机时完成--表明等待所有线程执行完
            //threadPool.setAwaitTerminationSeconds(60 * 15);// 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
            threadPool.setKeepAliveSeconds(keepAlive);
            //  线程名称前缀
            threadPool.setThreadNamePrefix("MyAsync-");
            threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            // 初始化
            threadPool.initialize();
            LOG.info("--------------------------》》》开启自定义线程池");
            return threadPool;
        } else {
            //启用默认的线程池
            return null;
        }
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        if (enable) {
            return new AsyncUncaughtExceptionHandler() {
                @Override
                public void handleUncaughtException(Throwable arg0, Method arg1, Object... arg2) {
                    LOG.error("==========================" + arg0.getMessage() + "=======================", arg0);
                    LOG.error("exception method:" + arg1.getName());
                }
            };
        } else {
            return null;
        }
    }
}

package cn.com.sharinglife.bean;

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
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig implements AsyncConfigurer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${my.threadpool.enable:false}")
    private boolean enable ; //是否启用自定义的线程池，否则启用默认的线程池

    @Value("${my.threadpool.corePoolSize:5}")
    private int corePoolSize; //线程池维护线程的最少数量

    @Value("${my.threadpool.maxPoolSize:10}")
    private int maxPoolSize ; //线程池维护线程的最大数量

    @Value("${my.threadpool.queueCapacity:2}")
    private int queueCapacity ; //缓存队列大小

    @Value("${my.threadpool.keepAlive:60}")
    private int keepAlive ; //允许的空闲时间

    @Override
    public Executor getAsyncExecutor() {
        if(enable){
            ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
            threadPool.setCorePoolSize(corePoolSize);//当前线程数
            threadPool.setMaxPoolSize(maxPoolSize);// 最大线程数
            threadPool.setQueueCapacity(queueCapacity);//线程池所使用的缓冲队列
            //threadPool.setWaitForTasksToCompleteOnShutdown(true);//等待任务在关机时完成--表明等待所有线程执行完
            //threadPool.setAwaitTerminationSeconds(60 * 15);// 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
            threadPool.setKeepAliveSeconds(keepAlive);
            threadPool.setThreadNamePrefix("MyAsync-");//  线程名称前缀
            threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            threadPool.initialize(); // 初始化
            logger.info("--------------------------》》》开启自定义线程池");
            return threadPool;
        }else{
            return null;  //启用默认的线程池
        }
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        if(enable){
            return new AsyncUncaughtExceptionHandler() {
                @Override
                public void handleUncaughtException(Throwable arg0, Method arg1, Object... arg2) {
                    logger.error("=========================="+arg0.getMessage()+"=======================", arg0);
                    logger.error("exception method:"+arg1.getName());
                }
            };
        }else{
            return null;
        }
    }
}

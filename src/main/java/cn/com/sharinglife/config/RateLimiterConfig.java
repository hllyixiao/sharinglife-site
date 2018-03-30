package cn.com.sharinglife.config;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 限制接口访问量（限流）
 * Created by hell on 2018/2/6
 */
@Configuration
public class RateLimiterConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${my.ratelimiter.number1:1.0}")
    private double number1; //线程池维护线程的最少数量

    @Value("${my.ratelimiter.number2:5.0}")
    private double number2; //线程池维护线程的最少数量

    @Bean(name = "myRateLimiter1")
    public RateLimiter getRateLimiter1(){
        return RateLimiter.create(number1);
    }

    @Bean(name = "myRateLimiter2")
    public RateLimiter getRateLimiter2(){
        return RateLimiter.create(number2);
    }
}

package cn.com.sharinglife.config;

import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Configuration;

/**
 * 1、Hystrix Dashboard是作为断路器状态的一个组件，
 * 提供了数据监控和友好的图形化界面。
 * 2、http://localhost:8031/hystrix.stream 可以看到具体数据
 * 3、http://localhost:8031/hystrix 图形界面，然后再里面输入
 * Created by hell on 2018/2/8
 *
 * @author hell
 */
@Configuration
@EnableHystrix
@EnableHystrixDashboard
public class HystrixDashboardConfig {
}

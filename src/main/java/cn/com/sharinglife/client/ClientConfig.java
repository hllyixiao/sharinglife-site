package cn.com.sharinglife.client;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 该配置类表示整个项目可以实现：服务注册、负载均衡、服务熔断功能
 * <p>
 * Created by hell on 2018/2/5
 */
@Configuration
@EnableEurekaClient
@EnableFeignClients
public class ClientConfig {

    /**
     * 默认开始负载均衡
     *
     * @return
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

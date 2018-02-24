package cn.com.sharinglife.client;

import cn.com.sharinglife.client.contains.ModelContain;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hell on 2018/2/5
 */
@FeignClient(value = ModelContain.SERVICE_NAME)
public interface ModelClient {
    @GetMapping(value = "/home")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}

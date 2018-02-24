package cn.com.sharinglife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
public class SharinglifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharinglifeApplication.class, args);
	}
}

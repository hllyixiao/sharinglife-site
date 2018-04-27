package cn.com.sharinglife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author hell
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class})
@EnableAutoConfiguration
public class SharinglifeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharinglifeApplication.class, args);
    }
}

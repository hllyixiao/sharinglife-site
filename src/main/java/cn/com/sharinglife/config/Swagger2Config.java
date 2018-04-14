package cn.com.sharinglife.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2构建强大的RESTful API文档
 * <p>
 * 通过 http://localhost:8031/swagger-ui.html 直接访问
 * <p>
 * 解决的问题：
 * 1、文档需要更新的时候，需要再次发送一份给前端，也就是文档更新交流不及时。
 * 2、接口返回结果不明确
 * 3、不能直接在线测试接口，通常需要使用工具，比如postman
 * 4、接口文档太多，不好管理
 * Created by hell on 2018/2/7
 *
 * @author hell
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * swagger扫描的controller包路径
     */
    private String basePackage = "cn.com.sharinglife.controller";
    /**
     * swagger文档标题信息
     */
    private String title = "本项目是利用swagger构建Restful api文档";
    /**
     * swagger文档描述
     */
    private String description = "简单优雅的restful api风格，可以更好的管理接口并且可以直接测试";
    /**
     * swagger文档版本
     */
    private String version = "2.0";


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl("http://www.baidu.com")
                .version(version)
                .build();
    }

}

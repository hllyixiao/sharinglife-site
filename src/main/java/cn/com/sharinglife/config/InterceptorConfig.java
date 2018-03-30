package cn.com.sharinglife.config;

import cn.com.sharinglife.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by hell on 2018/2/2
 * 拦截器
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有请求,注册用于验证登陆权限的拦截器
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    /**
     * 静态资源处理（自定义配置）
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //其中SLFile表示访问的前缀。"file:E:/SLFile/"是文件真实的存储路径(上传的图片在E盘下的SLFile目录下)
        //因此可以通过http://localhost:8031/SLFile/avatar/1/8.png来展示图片
        registry.addResourceHandler("/SLFile/**").addResourceLocations("file:E:/SLFile/");
    }
}

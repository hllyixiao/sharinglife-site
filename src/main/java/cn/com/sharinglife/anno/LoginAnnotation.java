package cn.com.sharinglife.anno;

import java.lang.annotation.*;

/**
 * 自定义登陆权限注解
 * Created by hell on 2018/2/2
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginAnnotation {
}


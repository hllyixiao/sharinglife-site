package cn.com.sharinglife.interceptor;

import cn.com.sharinglife.anno.LoginAnnotation;
import cn.com.sharinglife.pojo.User;
import cn.com.sharinglife.service.UserService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Objects;


/**
 * Created by hell on 2018/2/2
 * 通过拦截器的方式实现自定义
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    // 在调用方法之前执行拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 将handler强转为HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        // 获取出方法上的LoginAnnotation注解
        LoginAnnotation loginAnnotation = method.getAnnotation(LoginAnnotation.class);
        // 如果登陆验证不为null,表示需要登陆验证
        if (Objects.nonNull(loginAnnotation)) {
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("user");
            if(!(obj instanceof User)) {
                String userId = request.getHeader("user_id");
                if(Objects.nonNull(userId)){
                    User user = userService.getUserById(Integer.parseInt(userId));
                    if(Objects.isNull(user)){
                        response.setStatus(HttpStatus.SC_NOT_IMPLEMENTED);
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }
        // 返回true表示不用拦截
        return true;
    }
}

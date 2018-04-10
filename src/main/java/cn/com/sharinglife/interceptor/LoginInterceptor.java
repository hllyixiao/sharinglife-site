package cn.com.sharinglife.interceptor;

import cn.com.sharinglife.anno.LoginAnnotation;
import cn.com.sharinglife.comment.Const;
import cn.com.sharinglife.enums.FrontUrlEnum;
import org.springframework.beans.factory.annotation.Value;
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
 * @author hell
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Value("${my.annotation.login:false}")
    private boolean isOpenLoginAnnotation;

    /**
     * 在调用方法之前执行拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!isOpenLoginAnnotation){
            return true;
        }
        // 将handler强转为HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        // 获取出方法上的LoginAnnotation注解
        LoginAnnotation loginAnnotation = method.getAnnotation(LoginAnnotation.class);
        // 如果登陆验证不为null,表示需要登陆验证
        if (Objects.nonNull(loginAnnotation)) {
            HttpSession session = request.getSession();
            Object obj = session.getAttribute(Const.SESSION_USER_KEY);
            if(Objects.isNull(obj)){
                response.sendRedirect(FrontUrlEnum.LOGIN_PAGE.getUrl());
                return false;
            }
        }
        // 返回true表示不用拦截
        return true;
    }
}

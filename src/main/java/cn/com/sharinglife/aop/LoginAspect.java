package cn.com.sharinglife.aop;

import cn.com.sharinglife.anno.LoginAnnotation;
import cn.com.sharinglife.enums.FrontUrlEnum;
import cn.com.sharinglife.util.Const;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 用户登陆验证，只对注解LoginAnnotation有效
 * Created by hell on 2018/4/8
 *
 * @author hell
 */
@Aspect
@Component
public class LoginAspect {

    /**
     * 是否开启LoginAnnotation注解
     */
    @Value("${my.annotation.login:false}")
    private boolean isOpenLoginAnnotation;

    @Around(value = "@annotation(loginAnnotation)")
    public Object doLoginVerification(final ProceedingJoinPoint joinPoint,
                                      final LoginAnnotation loginAnnotation) throws Throwable {
        if (isOpenLoginAnnotation) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            //session是否存在user对象（判断用户是否登陆）
            Object obj = session.getAttribute(Const.SESSION_USER_KEY);
            if (!Objects.isNull(obj)) {
                HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                        .getRequestAttributes()).getResponse();
                response.setStatus(302);
                response.setHeader("Location", FrontUrlEnum.LOGIN_PAGE.getUrl());
                //response.setHeader("Status Code","302");
                //response.sendRedirect(FrontUrlEnum.LOGIN_PAGE.getUrl());
                return null;
            }
        }
        return joinPoint.proceed();
    }
}

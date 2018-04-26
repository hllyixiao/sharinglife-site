package cn.com.sharinglife.util;

import cn.com.sharinglife.pojo.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by hell on 2018/2/27
 *
 * @author hell
 */
public class SessionCookieUtil {

    public static void setSessionUser(HttpServletRequest request, User user) {
        if (user != null) {
            final HttpSession session = request.getSession();
            session.setAttribute(Const.SESSION_USER_KEY, user);
            session.setAttribute(Const.SESSION_USERID_KEY, user.getId());
            //单位为秒，在没有活动30分钟后，session将失效。设置为-1将永不关闭。
            session.setMaxInactiveInterval(30 * 60);
        }
    }

    public static void setCookies(HttpServletResponse response, String name, String value) {
        final Cookie newCookie = new Cookie(name, value);
        //设置Cookie的有效期为3天
        newCookie.setMaxAge(60 * 60 * 24 * 3);
        response.addCookie(newCookie);
    }

    public static User getCurrentUserByRequest(HttpServletRequest request) {
        return getCurrentUserBySession(request.getSession());
    }

    public static User getCurrentUserBySession(HttpSession httpSession) {
        Object user = httpSession.getAttribute(Const.SESSION_USER_KEY);
        if (user != null && user instanceof User) {
            return (User) user;
        }
        return null;
    }

    public static Integer getCurrentUserIdByRequest(HttpServletRequest request) {
        return (Integer) getCurrentUserIdBySession(request.getSession());
    }

    public static Integer getCurrentUserIdBySession(HttpSession httpSession) {
        return (Integer) httpSession.getAttribute(Const.SESSION_USERID_KEY);
    }

    public static String getCookieByName(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}

package cn.com.sharinglife.util;

import cn.com.sharinglife.pojo.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by hell on 2018/2/27
 */
public class SessionAndCookieUtil {

    public static void setSessionUser(HttpServletRequest request,User user){
        if(user != null){
            request.getSession().setAttribute("user", user);
        }
    }

    public static Cookie setCookies(String name, String value){
        //设置Cookie的有效期为3天
        Cookie newCookie = new Cookie(name, value);
        newCookie.setMaxAge(60 * 60 * 24 * 3);
        return newCookie;
    }

    public static User getUserBySession(HttpServletRequest request){
        Object user = request.getAttribute("user");
        if(user != null && user instanceof User){
            return (User)user;
        }
        return null;
    }

    public static String getCookieByName(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(name)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}

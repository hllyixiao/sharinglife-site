package cn.com.sharinglife.async;

import cn.com.sharinglife.pojo.User;
import cn.com.sharinglife.util.Functions;
import cn.com.sharinglife.util.SessionAndCookieUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by hell on 2018/2/27
 */
@Component
public class LoginAsync {
    //登陆成功后的一些操作
    @Async
    public void longinAfter(HttpServletRequest request, User user) throws InterruptedException {
        //设置session
        User updateUser = new User();
        updateUser.setId(user.getId());
        SessionAndCookieUtil.setSessionUser(request,user);
        String ip = Functions.getIpAddr(request);
        updateUser.setUserLastLoginIp(ip);
        Thread.sleep(10000);
        updateUser.setUserLastLoginTime(new Date());
        // userService.updateUser(updateUser);
    }
}

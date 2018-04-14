package cn.com.sharinglife.async;

import cn.com.sharinglife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hell on 2018/2/27
 *
 * @author hell
 */
@Component
public class LoginAsync {

    @Autowired
    private UserService userService;

}

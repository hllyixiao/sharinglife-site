package cn.com.sharinglife.controller;

import cn.com.sharinglife.contains.LoginAndRegisterApis;
import cn.com.sharinglife.pojo.User;
import cn.com.sharinglife.pojo.data.LoginData;
import cn.com.sharinglife.pojo.data.RegisterData;
import cn.com.sharinglife.service.UserService;
import cn.com.sharinglife.util.VerifyCodeUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * Created by hell on 2018/2/9
 */
@RestController
public class LoginAndRegisterController {

    private final Logger LOG = LoggerFactory.getLogger(LoginAndRegisterController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "生成验证码图片")
    @GetMapping(value = LoginAndRegisterApis.VERIFY_CODE)
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //利用图片工具生成图片
        //第一个参数是生成的验证码数字，第二个参数是生成的图片
        Object[] objs = VerifyCodeUtil.createImage();
        //将验证码数字存入Session
        HttpSession session = request.getSession();
        session.setAttribute("verifyCodeNum",objs[0]);

        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping(value = LoginAndRegisterApis.REGISTER)
    public boolean register(HttpServletResponse response,
                            @RequestBody RegisterData registerData){
        LOG.info("register - 用户注册");
        if(registerData.nonNull()){
            User user = new User(registerData);
            userService.addUser(user);
            return true;
        }
        LOG.error("register方法 - 参数registerData不能有为null的属性");
        response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
        return false;
    }

    @ApiOperation(value = "用户登陆",notes = "返回用户信息，没有则返回null")
    @PostMapping(value = LoginAndRegisterApis.LOGIN)
    public User login (HttpServletResponse response,
                       @RequestBody LoginData loginData){
        LOG.info("login — 用户登陆");
        if(loginData.nonNull()){
            User user = new User(loginData);
            return userService.getUser(user);
        }
        LOG.error("login — 参数loginData不符合条件");
        response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
        return null;
    }

    @ApiOperation(value = "判断手机号是否被注册")
    @ApiImplicitParam(name = "phone",value = "合法的手机号",required = true,paramType = "query")
    @GetMapping(value = LoginAndRegisterApis.IS_EXITS_PHONE)
    public boolean isExistPhone(HttpServletResponse response,
                                @RequestParam("phone")String phone){
        LOG.info("isExistPho — 判断手机号是否被注册");
        if(Objects.nonNull(phone)){
            return userService.isExistPho(phone);
        }
        LOG.error("isExistPho — 参数 phone 不能为null");
        response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
        return false;
    }
}

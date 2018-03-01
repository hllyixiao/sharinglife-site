package cn.com.sharinglife.controller;

import cn.com.sharinglife.async.LoginAsync;
import cn.com.sharinglife.containapis.LoginAndRegisterApis;
import cn.com.sharinglife.pojo.User;
import cn.com.sharinglife.pojo.requestdata.LoginRequest;
import cn.com.sharinglife.pojo.requestdata.RegisterRequest;
import cn.com.sharinglife.pojo.responsedata.StateMsgResponse;
import cn.com.sharinglife.service.UserService;
import cn.com.sharinglife.staticcomment.Const;
import cn.com.sharinglife.util.Digest;
import cn.com.sharinglife.util.Functions;
import cn.com.sharinglife.util.SessionCookieUtil;
import cn.com.sharinglife.util.VerifyCodeUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
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

/**
 * Created by hell on 2018/2/9
 */
@RestController
public class LoginRegisterController {

    private final Logger LOG = LoggerFactory.getLogger(LoginRegisterController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LoginAsync loginAsync;

    @ApiOperation(value = "生成验证码图片")
    @GetMapping(value = LoginAndRegisterApis.VERIFY_CODE)
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //利用图片工具生成图片
        //第一个参数是生成的验证码数字，第二个参数是生成的图片
        Object[] objs = VerifyCodeUtil.createImage();
        //将验证码数字存入Session
        HttpSession session = request.getSession();
        session.setAttribute(Const.VERIFY_CODE,objs[0]);

        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping(value = LoginAndRegisterApis.REGISTER)
    public boolean register(HttpServletResponse response,
                            @RequestBody RegisterRequest registerRequest){
        LOG.info("register - 用户注册");
        if(registerRequest.nonNull()){
            final User user = new User(registerRequest);
            userService.addUser(user);
            return true;
        }
        LOG.error("register方法 - 参数registerData不能有为null的属性");
        response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
        return false;
    }

    @ApiOperation(value = "用户登陆",notes = "登陆成功后，返回用户信息")
    @PostMapping(value = LoginAndRegisterApis.LOGIN)
    public StateMsgResponse login(HttpServletResponse response, HttpServletRequest request,
                                  HttpSession session, @RequestBody LoginRequest loginRequest) {
        LOG.info("login — 用户登陆");
        final StateMsgResponse stateMsgResponse = new StateMsgResponse();
        //传入的对象不能有null的属性
        if(loginRequest.nonNull()){
            if(loginRequest.getVerifyCode().equalsIgnoreCase(
                    (String) session.getAttribute(Const.VERIFY_CODE))){
                final User user = userService.getUserByLoginData(loginRequest);
                if(user == null){
                    stateMsgResponse.setStateCode(0);
                    stateMsgResponse.setMsg("手机号或邮箱不存在！");
                }else if(! loginRequest.getPassword().equals(user.getPassword())){
                    stateMsgResponse.setStateCode(0);
                    stateMsgResponse.setMsg("密码错误！");
                }else{
                    stateMsgResponse.setStateCode(1);
                    stateMsgResponse.setMsg("登陆成功！");
                    //登陆成功后，添加session、cookie等信息
                    longinAfter(request,response,user);
                    LOG.info("用户-[{}] 登陆成功！", loginRequest.getPhoneOrName());
                }
            }else{
                stateMsgResponse.setStateCode(0);
                stateMsgResponse.setMsg("验证码错误!");
            }
            return stateMsgResponse;
        }
        LOG.error("login — 参数loginData不符合条件");
        response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
        return null;
    }

    @ApiOperation(value = "退出登陆",notes = "退出登陆会清空session和cookie等信息")
    @GetMapping(value = LoginAndRegisterApis.LOGOUT)
    public void logout(HttpSession session){
        LOG.info("logout — 退出登陆");
        session.invalidate();
    }

    @ApiOperation(value = "判断手机号是否被注册")
    @ApiImplicitParam(name = "phone",value = "合法的手机号",required = true,paramType = "query")
    @GetMapping(value = LoginAndRegisterApis.IS_EXITS_PHONE)
    public boolean isExistPhone(HttpServletResponse response,
                                @RequestParam("phone")String phone){
        LOG.info("isExistPho — 判断手机号是否被注册");
        if(StringUtils.isNotBlank(phone)){
            return userService.isExistPho(phone);
        }
        LOG.error("isExistPho — 参数 phone 不能为null");
        response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
        return false;
    }

    @ApiOperation(value = "判断用户名是否被注册")
    @ApiImplicitParam(name = "name",value = "合法的用户名",required = true,paramType = "query")
    @GetMapping(value = LoginAndRegisterApis.IS_EXITS_NAME)
    public boolean isExistName(HttpServletResponse response,
                                @RequestParam(value = "name",required = true)String name){
        LOG.info("isExistName — 判断用户名是否被注册");
        if(StringUtils.isNotBlank(name)){
            return userService.isExistName(name);
        }
        LOG.error("isExistName — 参数 name 不能为null");
        response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
        return false;
    }

    //登陆成功后，添加session、cookie等信息
    public void longinAfter(HttpServletRequest request,
                            HttpServletResponse response, User user) {
        //更新用户信息，包括当前用户登陆的ip、时间
        final User updateUser = new User(user.getId());
        updateUser.setUserLastLoginIp(Functions.getIpAddr(request));
        userService.updateUser(updateUser);
        //设置session
        SessionCookieUtil.setSessionUser(request,user);
        //设置cookie
        final String encryptName = Digest.encryptBASE64(user.getName());
        final String cookieValue = String.valueOf(System.currentTimeMillis()) + encryptName;
        SessionCookieUtil.setCookies(response, Const.SL_COOKIE_NAME,cookieValue);
    }
}

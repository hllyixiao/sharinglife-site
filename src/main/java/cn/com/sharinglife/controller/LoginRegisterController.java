package cn.com.sharinglife.controller;

import cn.com.sharinglife.anno.LoginAnnotation;
import cn.com.sharinglife.comment.Const;
import cn.com.sharinglife.containapis.LoginAndRegisterApis;
import cn.com.sharinglife.enums.LogActionEnum;
import cn.com.sharinglife.pojo.Logs;
import cn.com.sharinglife.pojo.User;
import cn.com.sharinglife.pojo.requestdata.LoginRequest;
import cn.com.sharinglife.pojo.requestdata.RegisterRequest;
import cn.com.sharinglife.pojo.responsedata.CommonResponse;
import cn.com.sharinglife.service.LogsService;
import cn.com.sharinglife.service.UserService;
import cn.com.sharinglife.util.CommonUtil;
import cn.com.sharinglife.util.ImageUtil;
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by hell on 2018/2/9
 */
@RestController
public class LoginRegisterController {

    private final Logger LOG = LoggerFactory.getLogger(LoginRegisterController.class);

    private static final String DEFAULT_USER_AVATAR = "/SLFile/image/defaultpath/avatar/avatar.jpg";

    @Autowired
    private UserService userService;

    @Autowired
    private LogsService logsService;

    @ApiOperation(value = "生成验证码图片")
    @GetMapping(value = LoginAndRegisterApis.VERIFY_CODE)
    public Map getVerifyCode(HttpServletRequest request, HttpSession session) {
        //利用图片工具生成图片
        //第一个参数是生成的验证码数字，第二个参数是生成的图片
        Map res = new HashMap<String, String>();
        Object[] objs = VerifyCodeUtil.createImage();
        //将验证码数字存入Session
        session.setAttribute(Const.VERIFY_CODE, objs[0]);
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
        } catch (IOException e) {
            LOG.error("生成验证码图片报错 — ", e);
        }
        byte[] bytes = baos.toByteArray();
        res.put("verifyCodeImg", ImageUtil.verifyCodeImageToBase64(bytes));
        return res;
    }

    @LoginAnnotation
    @ApiOperation(value = "用户注册")
    @PostMapping(value = LoginAndRegisterApis.REGISTER)
    public boolean register(HttpServletResponse response,
                            @RequestBody RegisterRequest registerRequest) {
        LOG.info("register - 用户注册");
        if (registerRequest.nonNull()) {
            final User user = new User(registerRequest);
            user.setAvatarUrl(DEFAULT_USER_AVATAR);
            userService.addUser(user);
            return true;
        }
        LOG.error("register方法 - 参数registerData不能有为null的属性");
        response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
        return false;
    }

    @ApiOperation(value = "用户登陆", notes = "登陆成功后，返回用户信息")
    @PostMapping(value = LoginAndRegisterApis.LOGIN)
    public CommonResponse login(HttpServletResponse response,
                                HttpServletRequest request,
                                HttpSession session,
                                @RequestBody LoginRequest loginRequest) {
        LOG.info("login — 用户登陆");
        final CommonResponse commonResponse = new CommonResponse();
        //传入的对象不能有null的属性
        if (loginRequest.nonNull()) {
            if ("".equals(loginRequest.getVerifyCode()) || loginRequest.getVerifyCode()
                    .equalsIgnoreCase((String) session.getAttribute(Const.VERIFY_CODE))) {
                final User user = userService.getUserByLoginData(loginRequest);
                if (user == null) {
                    commonResponse.setStatusCode(0);
                    commonResponse.setMsg("手机号或邮箱不存在！");
                } else if (!loginRequest.getPassword().equals(user.getPassword())) {
                    commonResponse.setStatusCode(0);
                    commonResponse.setMsg("密码错误！");
                } else {
                    commonResponse.setStatusCode(1);
                    commonResponse.setMsg("登陆成功！");
                    commonResponse.setUser(user);
                    //登陆成功后，添加session、cookie等信息
                    longinAfter(request, response, user);
                    LOG.info("用户-[{}] 登陆成功！", loginRequest.getPhoneOrName());
                }
            } else {
                commonResponse.setStatusCode(0);
                commonResponse.setMsg("验证码错误!");
            }
            return commonResponse;
        }
        LOG.error("login — 参数loginData不符合条件");
        response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
        return null;
    }

    @ApiOperation(value = "退出登陆", notes = "退出登陆会清空session和cookie等信息")
    @GetMapping(value = LoginAndRegisterApis.LOGOUT)
    public void logout(HttpSession session) {
        LOG.info("logout — 退出登陆");
        session.invalidate();
    }

    @ApiOperation(value = "判断手机号是否被注册")
    @ApiImplicitParam(name = "phone", value = "合法的手机号", required = true, paramType = "query")
    @GetMapping(value = LoginAndRegisterApis.IS_EXITS_PHONE)
    public boolean isExistPhone(HttpServletResponse response,
                                @RequestParam("phone") String phone) {
        LOG.info("isExistPho — 判断手机号是否被注册");
        if (StringUtils.isNotBlank(phone)) {
            return userService.isExistPho(phone);
        }
        LOG.error("isExistPho — 参数 phone 不能为null");
        response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
        return false;
    }

    @ApiOperation(value = "判断用户名是否被注册")
    @ApiImplicitParam(name = "name", value = "合法的用户名", required = true, paramType = "query")
    @GetMapping(value = LoginAndRegisterApis.IS_EXITS_NAME)
    public boolean isExistName(HttpServletResponse response,
                               @RequestParam(value = "name") String name) {
        LOG.info("isExistName — 判断用户名是否被注册");
        if (StringUtils.isNotBlank(name)) {
            return userService.isExistName(name);
        }
        LOG.error("isExistName — 参数 name 不能为null");
        response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
        return false;
    }

    /**
     * 登陆成功后，添加session、cookie、log等信息
     *
     * @param request
     * @param response
     * @param user
     */
    public void longinAfter(HttpServletRequest request,
                            HttpServletResponse response, User user) {
        //更新用户信息，包括当前用户登陆的ip、时间
        final User updateUser = new User(user.getId());
        String ip = CommonUtil.getIpAddr(request);
        updateUser.setLastLoginIp(ip);
        userService.updateUser(updateUser);
        //添加日志信息到日志表
        Logs logs = new Logs(user.getId(), user.getName(), LogActionEnum.LOGIN.getAction(), ip);
        logsService.addLog(logs);
        //设置session
        SessionCookieUtil.setSessionUser(request, user);
        //设置cookie
        final String encryptName = CommonUtil.encryptBASE64(user.getName());
        final String cookieValue = String.valueOf(System.currentTimeMillis()) + encryptName;
        SessionCookieUtil.setCookies(response, Const.SL_COOKIE_NAME, cookieValue);
    }
}

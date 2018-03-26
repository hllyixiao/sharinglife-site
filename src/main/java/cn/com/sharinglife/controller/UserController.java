package cn.com.sharinglife.controller;

import cn.com.sharinglife.client.ModelClient;
import cn.com.sharinglife.containapis.UserApis;
import cn.com.sharinglife.enums.LogActionEnum;
import cn.com.sharinglife.pojo.Logs;
import cn.com.sharinglife.pojo.User;
import cn.com.sharinglife.pojo.responsedata.CommonResponse;
import cn.com.sharinglife.service.LogsService;
import cn.com.sharinglife.service.UserService;
import cn.com.sharinglife.util.CommonUtil;
import cn.com.sharinglife.util.SessionCookieUtil;
import com.github.pagehelper.PageInfo;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hell on 2018/1/31
 */
@Api(value="用户controller",tags={"用户操作接口"})
@RestController
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ModelClient modelClient;

    @Autowired
    private LogsService logsService;

    @Autowired
    @Qualifier(value = "myRateLimiter2")
    RateLimiter rateLimiter;


    @ApiOperation(value = "设置用户头像图片", notes = "设置用户头像图片")
    @PostMapping(value = UserApis.SET_USERS_AVATAR)
    public void setUserAvatar(HttpServletRequest request,HttpServletResponse response,
                              @RequestParam("file") final MultipartFile file) {
        LOG.info("setUserAvatar — 设置用户头像图片");
        User user = SessionCookieUtil.getUserBySession(request);
        if(Objects.nonNull(user)){
            Map<String,String> res = CommonUtil.getUserFilePath(file, true, user.getId());
            //后缀名
            String suffixName = res.get("suffixName");
            //用户数据库保存路径
            String dataBasePath = res.get("userPath");
            //用户磁盘保存路径
            String diskPath = res.get("diskPath");
            //用户头像路径
            String diskAvatarPath = diskPath + "avatar//avatar" + suffixName;
            LOG.info("用户图像全路径为：" + diskAvatarPath);
            //用户数据库头像路径
            String databaseAvatarPath = dataBasePath + "avatar//avatar" + suffixName;

            File dest = new File(diskAvatarPath);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                if(! databaseAvatarPath.equals(user.getAvatarUrl())){
                    userService.updateAvatarUrl(databaseAvatarPath,user.getId());
                    user.setAvatarUrl(databaseAvatarPath);
                }
            } catch (IOException e) {
                LOG.error("setUserAvatar — 设置用户头像出错 —",e);
            }
        }else{
            LOG.error("setUserAvatar — user为null，请登陆！");
            response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
        }
    }


    @ApiOperation(value = "添加关注", notes = "添加关注用户")
    @GetMapping(value = UserApis.ADD_USERS_FOLLOWER)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户Id",required = true, dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "followerId",value = "被关注的用户Id",required = true, paramType = "query")})
    public CommonResponse addFollower(@RequestParam(value = "userId") final Integer userId,
                                      @RequestParam(value = "followerId") final Integer followerId){
        LOG.info("addFollower — 添加关注");
        CommonResponse commonResponse = new CommonResponse();
        boolean isExistFollower = userService.isExistFollower(userId, followerId);
        if(isExistFollower){
            commonResponse.setStatusCode(1);
            commonResponse.setMsg("该用户已关注！");
            LOG.info("addFollower — 该用户已关注！");
        }else{
            userService.addMyFollower(userId,followerId);
            commonResponse.setStatusCode(1);
            commonResponse.setMsg("关注成功");
            LOG.info("addFollower — 关注成功！");
        }
        return commonResponse;
    }


    @ApiOperation(value = "取消关注", notes = "取消关注")
    @GetMapping(value = UserApis.DELETE_USERS_FOLLOWER)
    public CommonResponse deleteFollower(
                                         @RequestParam(value = "userId") final Integer userId,
                                         @RequestParam(value = "followerId") final Integer followerId){
        LOG.info("deleteFollower — 取消关注");
        CommonResponse commonResponse = new CommonResponse();
        userService.deleteFollower(userId,followerId);
        LOG.info("deleteFollower — 取消关注成功！");
        commonResponse.setStatusCode(1);
        commonResponse.setMsg("已取消关注！");
        return commonResponse;
    }


    @ApiOperation(value = "关注我的用户", notes = "获取所有关注我的用户信息")
    @GetMapping(value = UserApis.GET_FOLLOW_TO_ME_USERS)
    public List<User> getFollowToMeUsers(@RequestParam(value = "userId") final Integer userId){
        LOG.info("getFollowToMeUsers — 所有关注我的用户");
        return userService.followToMeUsers(userId);
    }


    @ApiOperation(value = "我关注的用户", notes = "获取所有我关注的用户信息")
    @GetMapping(value = UserApis.GET_MY_FOLLOW_USERS)
    public List<User> getMyFollowUsers(@RequestParam(value = "userId") final Integer userId){
        LOG.info("getMyFollowUsers — 所有关注我的用户");
        return userService.myFollowUsers(userId);
    }


    @ApiOperation(value = "修改用户密码", notes = "修改用户密码")
    @GetMapping(value = UserApis.MODIFY_PASSWORD_USERS)
    public CommonResponse modifyPassword(@RequestParam(value = "userId") final Integer userId,
                                         @RequestParam(value = "newPassword") final String newPassword){
        LOG.info("modifyPassword — 修改用户密码");
        CommonResponse commonResponse = new CommonResponse();
        User user = userService.getUserById(userId);
        if(user.getPassword().equals(newPassword)){
            commonResponse.setStatusCode(0);
            commonResponse.setMsg("新密码不能与旧密码相同！");
        }else{
            //添加成功信息到日志表
            Logs logs = new Logs(user.getId(),user.getName(), LogActionEnum.UP_PWD.getAction(),
                    "修改密码成功",user.getLastLoginIp());
            logsService.addLog(logs);

            commonResponse.setStatusCode(1);
            userService.updateUser(new User(userId,newPassword));
            commonResponse.setMsg("密码修改成功！");
        }
        return commonResponse;
    }


    @ApiOperation(value = "获取用户列表", notes = "获取用户列表信息")
    @GetMapping(value = UserApis.GET_USERS)
    public PageInfo<User> getUsers(@RequestParam(value = "page", defaultValue = "1") final int page,
                                 @RequestParam(value = "limit", defaultValue = "15") final int limit) {
        LOG.info("getUsers —— 获取用户列表，当前页:第{}页，每页获取{}个用户信息",page,limit);
        PageInfo<User> userPageInfo =  userService.getAllUsers(page,limit);
        return userPageInfo;
    }


    @ApiOperation(value = "通过用户id获取信息",notes = "根据id来获取用户详细信息")
    @ApiImplicitParam(name = "id",value = "用户id",required = true, paramType = "path")
    @GetMapping(value = UserApis.GET_USERS_BY_ID + "/{id}")
    public User getUserById(HttpServletResponse response,
                            @PathVariable final Integer id) {
        LOG.info("getUserById — 通过用户id获取信息 用户Id:",id);
        if(Objects.nonNull(id)){
            User user = userService.getUserById(id);
            if(Objects.nonNull(user)){
                return user;
            }
            LOG.error("getUserById — 不存在id={} 的用户",id);
            response.setStatus(HttpStatus.SC_BAD_REQUEST);
            return null;
        }
        LOG.error("getUserById — 用户id不能为null");
        response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
        return null;
    }


    @ApiOperation(value = "更新用户信息",notes = "更新用户信息")
    @GetMapping(value = UserApis.UPDATE_USER)
    public void updateUser(HttpServletResponse response,
                            @RequestBody final User user) {
        LOG.info("updateUser — 更新用户信息");
        if(Objects.nonNull(user.getId())){
            userService.updateUser(user);
        }
        LOG.error("updateUser — 参数user的id不能为null");
        response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
    }


    @GetMapping(value = "/home")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "70"),
            @HystrixProperty(name = "execution.timeout.enabled", value = "true")},
            fallbackMethod = "hiError")
    public String home(@RequestParam String name) throws InterruptedException {
        LOG.info("测试服务熔断");
        Thread.sleep(1000);
        return "";
        //return modelClient.sayHiFromClientOne(name);
    }

    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }

//    @ApiOperation(value = "测试swagger",notes = "根据url的id来获取用户详细信息")
//    @GetMapping(value = "/h")
//    @ApiIgnore//使用该注解忽略这个API
//    public String getString() throws InterruptedException, IOException {
//        if(true){
//            Object[] objs = VerifyCodeUtil.createImage();
//            BufferedImage image = (BufferedImage) objs[1];
//            OutputStream os = new FileOutputStream("E:/1.png");
//            ImageIO.write(image, "png", os);
//            os.close();
//            return "";
//        }
//
//        if(!rateLimiter.tryAcquire()){
//            System.out.println("hhh"+a);
//            a++;
//            Thread.sleep(300);
//            return "hhh"+a;
//        }
//        System.out.println("获取令牌失败");
//        return "获取令牌失败";
//    }
}

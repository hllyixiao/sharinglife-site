package cn.com.sharinglife.controller;

import cn.com.sharinglife.client.ModelClient;
import cn.com.sharinglife.containapis.UserApis;
import cn.com.sharinglife.pojo.User;
import cn.com.sharinglife.pojo.responsedata.StateMsgResponse;
import cn.com.sharinglife.service.UserService;
import cn.com.sharinglife.util.SessionCookieUtil;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
import java.util.Objects;

/**
 * Created by hell on 2018/1/31
 */
@RestController
@Api("关于用户信息的控制器")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ModelClient modelClient;

    @Autowired
    @Qualifier(value = "myRateLimiter2")
    RateLimiter rateLimiter;

    private static final String AVATAR_PATH = "E://avatar//";

    @ApiOperation(value = "设置用户头像图片", notes = "设置用户头像图片")
    @PostMapping(value = UserApis.SET_USERS_AVATAR)
    public void setUserAvatar(HttpServletRequest request,
                              @RequestParam("file")MultipartFile file) {
        // 获取文件全名
        String fileName = file.getOriginalFilename();
        LOG.info("文件全名：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        LOG.info("上传的后缀名为：" + suffixName);
        // 文件上传后设置的路径的路径
        User user = SessionCookieUtil.getUserBySession(request);
        String filePath = getPathUrl(user.getId()) + suffixName;

        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            if(! filePath.equals(user.getAvatarUrl())){
                userService.updateAvatarUrl(filePath,user.getId());
                user.setAvatarUrl(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "添加关注", notes = "添加关注")
    @GetMapping(value = UserApis.ADD_USERS_FOLLOWER)
    public StateMsgResponse addFollower(HttpServletResponse response,
                                        @RequestParam(value = "userId", required = true) Integer userId,
                                        @RequestParam(value = "followerId", required = true) Integer followerId){
        LOG.info("addFollower — 添加关注");
        StateMsgResponse stateMsgResponse = new StateMsgResponse();

        boolean isExistFollower = userService.isExistFollower(userId, followerId);
        if(isExistFollower){
            stateMsgResponse.setStateCode(0);
            stateMsgResponse.setMsg("该用户已关注！");
        }else{
            userService.addMyFollower(userId,followerId);
            stateMsgResponse.setStateCode(1);
            stateMsgResponse.setMsg("关注成功");
        }
        LOG.info("addFollower — 添加关注成功！");
        return stateMsgResponse;
    }

    @ApiOperation(value = "取消关注", notes = "取消关注")
    @GetMapping(value = UserApis.DELETE_USERS_FOLLOWER)
    public StateMsgResponse deleteFollower(HttpServletResponse response,
                                           @RequestParam(value = "userId", required = true) Integer userId,
                                           @RequestParam(value = "followerId", required = true) Integer followerId){
        LOG.info("deleteFollower — 取消关注");
        StateMsgResponse stateMsgResponse = new StateMsgResponse();
        userService.deleteFollower(userId,followerId);
        LOG.info("deleteFollower — 取消关注成功！");
        stateMsgResponse.setStateCode(1);
        stateMsgResponse.setMsg("已取消关注！");
        return stateMsgResponse;
    }

    @ApiOperation(value = "获取所有关注用户", notes = "获取所有我关注的用户信息")
    @GetMapping(value = UserApis.GET_USERS_FOLLOWER)
    public List<User> getFollower(HttpServletResponse response,
                                      @RequestParam(value = "userId", required = true) Integer userId){
        LOG.info("getFollower — 获取所有关注用户");
        return userService.getMyFollowerUser(userId);
    }

    @ApiOperation(value = "获取所有用户信息", notes = "获取所有用户信息")
    @GetMapping(value = UserApis.GET_ALL_USERS_URL)
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

//    @ApiOperation(value = "获取用户信息", notes = "通过用户年龄和用户姓名来获取所有用户信息")
//    @GetMapping(value = UserApis.GET_USERS_URL)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "age",value = "用户年龄",required = true, dataType = "int",paramType = "query"),
//            @ApiImplicitParam(name = "name",value = "用户姓名",required = true, paramType = "query")})
//    public User getUser(@RequestParam(value = "age") Integer age,
//                        @RequestParam(value = "name") String name) {
//        logger.info("UserController - getUser");
//        return userService.getByAgeAndName(age,name);
//    }

    //@LoginAnnotation
    @ApiOperation(value = "获取用户信息",notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id",value = "用户id",required = true, paramType = "path")
    @GetMapping(value = UserApis.GET_USERS_BY_ID_URL + "/{id}")
    public User getUserById(HttpServletResponse response,
                            @PathVariable final Integer id) {
        LOG.info("getUserById — 获取用户信息 用户Id:",id);
        if(Objects.nonNull(id)){
            User user = userService.getUserById(id);
            if(Objects.nonNull(user)){
                return user;
            }
            LOG.error("getUserById — 不存在id=" + id + "的用户");
            response.setStatus(HttpStatus.SC_BAD_REQUEST);
            return null;
        }
        LOG.error("getUserById — 用户id不能为null");
        response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
        return null;
    }

    //@LoginAnnotation
    @ApiOperation(value = "更新用户信息",notes = "更新用户信息")
    @GetMapping(value = UserApis.UPDATE_USERS_URL)
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

    public String getPathUrl(Integer id){
        String path;
        //获取二级路径
        if(id < 1000){
            path =  "1";
        }else{
            path =  String.valueOf(id / 1000);
        }
        return AVATAR_PATH + path + "//" + id;
    }
}

package cn.com.sharinglife.controller;

import cn.com.sharinglife.client.ModelClient;
import cn.com.sharinglife.contains.UserApis;
import cn.com.sharinglife.pojo.User;
import cn.com.sharinglife.service.UserService;
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

import javax.servlet.http.HttpServletResponse;
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
}

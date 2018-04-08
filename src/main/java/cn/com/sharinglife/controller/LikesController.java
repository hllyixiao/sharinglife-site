package cn.com.sharinglife.controller;

import cn.com.sharinglife.anno.LoginAnnotation;
import cn.com.sharinglife.containapis.LikesApis;
import cn.com.sharinglife.service.LikesService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hell
 * @data 2018/3/27 18:24
 */
@RestController
public class LikesController {

    private final Logger LOG = LoggerFactory.getLogger(LikesController.class);

    @Autowired
    private LikesService likesService;

    @LoginAnnotation
    @ApiOperation(value = "点赞")
    @GetMapping(value = LikesApis.ADD_LIKE)
    public boolean addLike(@RequestParam(value = "id") Integer id,
                           @RequestParam(value = "userId") Integer userId,
                           @RequestParam(value = "type") Integer type){
        LOG.info("addLike - 点赞");
        likesService.addLike(id,userId,type);
        return true;
    }

    @LoginAnnotation
    @ApiOperation(value = "取消点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type",value = "点赞模块对象的类型，1：文章、2：图片、3：视频",
                    required = true, dataType = "int",paramType = "query")})
    @GetMapping(value = LikesApis.DELETE_LIKE)
    public boolean deleteLike(@RequestParam(value = "id") Integer id,
                           @RequestParam(value = "userId") Integer userId,
                           @RequestParam(value = "type") Integer type){
        LOG.info("deleteLike - 取消点赞");
        likesService.deleteLike(id,userId,type);
        return true;
    }
}

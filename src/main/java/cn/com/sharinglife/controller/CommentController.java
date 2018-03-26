package cn.com.sharinglife.controller;

import cn.com.sharinglife.containapis.CommentApis;
import cn.com.sharinglife.pojo.Comment;
import cn.com.sharinglife.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author Created by hell on 2018/3/16
 *
 */
@Api(value="评论controller",tags={"评论接口"})
@RestController
public class CommentController {

    private final Logger LOG = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "添加评论", notes = "添加评论")
    @PostMapping(value = CommentApis.ADD_COMMENT)
    public boolean addComment(@RequestBody final Comment comment){
        LOG.info(" addComment - 添加评论");
        commentService.addComment(comment);
        return true;
    }

    @ApiOperation(value = "删除评论", notes = "删除评论")
    @GetMapping(value = CommentApis.DELETE_COMMENT + "/{commentId}")
    public boolean deleteComment(@PathVariable final Integer commentId){
        LOG.info(" addComment - 删除评论");
        if(Objects.nonNull(commentId)){
            commentService.deleteComment(commentId);
            LOG.info("删除评论成功！commentId = {}",commentId);
            return true;
        }
        LOG.info("删除评论失败，参数commentId 不能为null！");
        return false;
    }
}

package cn.com.sharinglife.controller;

import cn.com.sharinglife.anno.LoginAnnotation;
import cn.com.sharinglife.apis.CommentApis;
import cn.com.sharinglife.pojo.Comment;
import cn.com.sharinglife.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 评论控制器
 *
 * @author Created by hell on 2018/3/16
 */
@RestController
public class CommentController {

    private final Logger LOG = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @LoginAnnotation
    @ApiOperation(value = "添加评论", notes = "添加评论")
    @PostMapping(value = CommentApis.ADD_COMMENT)
    public boolean addComment(@RequestBody final Comment comment) {
        LOG.info(" addComment - 添加评论");
        if (comment.nonNull()) {
            commentService.addComment(comment);
            return true;
        }
        LOG.error(" addComment - 添加评论 失败！comment有些参数不能为null");
        return false;
    }

    @LoginAnnotation
    @ApiOperation(value = "删除评论", notes = "删除评论")
    @GetMapping(value = CommentApis.DELETE_COMMENT)
    public boolean deleteComment(@RequestParam("commentId") final Integer commentId,
                                 @RequestParam("iid") final Integer iid,
                                 @RequestParam("type") final Integer type) {
        LOG.info(" deleteComment - 删除评论");
        if (Objects.nonNull(commentId) && Objects.nonNull(type)) {
            commentService.deleteComment(commentId, iid, type);
            LOG.info("删除评论成功！commentId = {}", commentId);
            return true;
        }
        LOG.info("删除评论失败，参数commentId 不能为null！");
        return false;
    }
}

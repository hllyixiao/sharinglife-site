package cn.com.sharinglife.service.impl;

import cn.com.sharinglife.enums.ModularEnum;
import cn.com.sharinglife.mapper.CommentMapper;
import cn.com.sharinglife.pojo.Comment;
import cn.com.sharinglife.service.ArticleService;
import cn.com.sharinglife.service.CommentService;
import cn.com.sharinglife.service.PictureService;
import cn.com.sharinglife.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hell on 2018/3/16
 * @author hell
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class CommentServiceImpl implements CommentService {

    private final Logger LOG = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void addComment(Comment comment) {
        if(ModularEnum.ARTICLE_TYPE.getTypeId().equals(comment.getType())){
            LOG.info(" 用户id={} 评论 id={}的用户,文章id={}",comment.getUserId(),
                    comment.getOwnerUserId(),comment.getIid());
            commentMapper.addCommentArticle(comment);
            articleService.addComment(comment.getIid());
        }else if(ModularEnum.PICTURE_TYPE.getTypeId().equals(comment.getType())){
            LOG.info(" 用户id={} 评论 id={}的用户,图片id={}",comment.getUserId(),
                    comment.getOwnerUserId(),comment.getIid());
            commentMapper.addCommentPicture(comment);
        }else if(ModularEnum.VIDEO_TYPE.getTypeId().equals(comment.getType())){
            LOG.info(" 用户id={} 评论 id={}的用户,视频id={}",comment.getUserId(),
                    comment.getOwnerUserId(),comment.getIid());
            commentMapper.addCommentVideo(comment);
        }
    }

    @Override
    public void deleteComment(Integer commentId,Integer iid,Integer type) {
        if(ModularEnum.ARTICLE_TYPE.getTypeId().equals(type)){
            LOG.info(" 用户删除 文章评论commentId={} 的评论",commentId);
            commentMapper.deleteCommentArticle(commentId);
            articleService.deleteComment(iid);
        }else if(ModularEnum.PICTURE_TYPE.getTypeId().equals(type)){
            LOG.info(" 用户删除 图片评论commentId={} 的评论",commentId);
            commentMapper.deleteCommentPicture(commentId);
        }else if(ModularEnum.VIDEO_TYPE.getTypeId().equals(type)){
            LOG.info(" 用户删除 视频评论commentId={} 的评论",commentId);
            commentMapper.deleteCommentVideo(commentId);
        }
    }
}

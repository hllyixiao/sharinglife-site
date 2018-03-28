package cn.com.sharinglife.service.impl;

import cn.com.sharinglife.controller.LikesController;
import cn.com.sharinglife.enums.LikesEnum;
import cn.com.sharinglife.mapper.LikesMapper;
import cn.com.sharinglife.service.ArticleService;
import cn.com.sharinglife.service.LikesService;
import cn.com.sharinglife.service.PictureService;
import cn.com.sharinglife.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hell
 * @data 2018/3/27 19:18
 */
@Service
public class LikesServiceImpl implements LikesService {

    private final Logger LOG = LoggerFactory.getLogger(LikesServiceImpl.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private LikesMapper likesMapper;

    @Override
    public void addLike(Integer id, Integer userId, Integer type) {
        if(type == LikesEnum.ARTICLE_TYPE.getTypeId()){
            LOG.info("用户id = {}为文章id = {} 点赞",userId,id);
            likesMapper.addLikeArticle(id,userId);
            articleService.addLike(id);
        }else if(type == LikesEnum.PICTURE_TYPE.getTypeId()){
            LOG.info("用户id = {}为图片id = {} 点赞",userId,id);
            likesMapper.addLikePicture(id,userId);
        }else if(type == LikesEnum.VIDEO_TYPE.getTypeId()){
            LOG.info("用户id = {}为视频id = {} 点赞",userId,id);
            likesMapper.addLikeVideo(id,userId);
        }
    }

    @Override
    public void deleteLike(Integer id, Integer userId, Integer type) {
        if(type == LikesEnum.ARTICLE_TYPE.getTypeId()){
            LOG.info("用户id = {}为文章id = {} 取消点赞",userId,id);
            likesMapper.deleteLikeArticle(id,userId);
            articleService.deleteLike(id);
        }else if(type == LikesEnum.PICTURE_TYPE.getTypeId()){
            LOG.info("用户id = {}为图片id = {} 取消点赞",userId,id);
            likesMapper.deleteLikePicture(id,userId);
        }else if(type == LikesEnum.VIDEO_TYPE.getTypeId()){
            LOG.info("用户id = {}为视频id = {} 取消点赞",userId,id);
            likesMapper.deleteLikeVideo(id,userId);
        }
    }
}

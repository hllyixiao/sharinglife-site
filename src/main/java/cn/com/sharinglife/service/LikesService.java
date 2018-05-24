package cn.com.sharinglife.service;

import org.apache.ibatis.annotations.Param;

/**
 * @author hell
 * @data 2018/3/27 19:17
 */
public interface LikesService {

    void addLike(Integer id, Integer userId, Integer type);

    void deleteLike(Integer id, Integer userId, Integer type);

    /**
     * 该用户是否对此篇文章点赞过
     *
     * @param articleId
     * @param userId
     */
    boolean isLikeArticleByUserId(Integer articleId, Integer userId);

    /**
     * 该用户是否对此篇图片点赞过
     *
     * @param articleId
     * @param userId
     */
    boolean isLikePictureByUserId(Integer articleId, Integer userId);

    /**
     * 该用户是否对此篇视频点赞过
     *
     * @param articleId
     * @param userId
     */
    boolean isLikeVideoByUserId(Integer articleId, Integer userId);
}

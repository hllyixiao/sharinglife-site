package cn.com.sharinglife.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author hell
 * @data 2018/3/27 19:36
 */
@Mapper
public interface LikesMapper {

    /**
     * 对文章内容点赞
     *
     * @param articleId
     * @param userId
     */
    void addLikeArticle(@Param("articleId") Integer articleId,
                        @Param("userId") Integer userId);

    /**
     * 对图片模块点赞
     *
     * @param pictureId
     * @param userId
     */
    void addLikePicture(@Param("pictureId") Integer pictureId,
                        @Param("userId") Integer userId);

    /**
     * 对视频模块点赞
     *
     * @param videoId
     * @param userId
     */
    void addLikeVideo(@Param("videoId") Integer videoId,
                      @Param("userId") Integer userId);

    /**
     * 取消对文章点赞
     *
     * @param articleId
     * @param userId
     */
    void deleteLikeArticle(@Param("articleId") Integer articleId,
                           @Param("userId") Integer userId);

    /**
     * 取消对图片模块点赞
     *
     * @param pictureId
     * @param userId
     */
    void deleteLikePicture(@Param("pictureId") Integer pictureId,
                           @Param("userId") Integer userId);

    /**
     * 取消对视频模块点赞
     *
     * @param videoId
     * @param userId
     */
    void deleteLikeVideo(@Param("videoId") Integer videoId,
                         @Param("userId") Integer userId);
}

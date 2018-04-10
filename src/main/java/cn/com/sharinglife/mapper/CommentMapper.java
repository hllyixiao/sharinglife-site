package cn.com.sharinglife.mapper;

import cn.com.sharinglife.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by hell on 2018/3/16
 * @author hell
 */
@Mapper
public interface CommentMapper {

    void addCommentArticle(Comment comment);

    void addCommentPicture(Comment comment);

    void addCommentVideo(Comment comment);

    void deleteCommentArticle(@Param("commentId") Integer commentId);

    void deleteCommentPicture(@Param("commentId") Integer commentId);

    void deleteCommentVideo(@Param("commentId") Integer commentId);
}

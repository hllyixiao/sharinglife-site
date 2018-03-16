package cn.com.sharinglife.mapper;

import cn.com.sharinglife.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by hell on 2018/3/16
 */
@Mapper
public interface CommentMapper {

    void addComment(Comment comment);

    void deleteComment(Integer commentId);
}

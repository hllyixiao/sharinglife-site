package cn.com.sharinglife.service;

import cn.com.sharinglife.pojo.Comment;

/**
 * Created by hell on 2018/3/16
 */
public interface CommentService {

    void addComment(Comment comment);

    void deleteComment(Integer commentId);
}

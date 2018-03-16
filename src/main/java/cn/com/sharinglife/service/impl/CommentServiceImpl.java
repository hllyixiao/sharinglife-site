package cn.com.sharinglife.service.impl;

import cn.com.sharinglife.mapper.CommentMapper;
import cn.com.sharinglife.pojo.Comment;
import cn.com.sharinglife.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by hell on 2018/3/16
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void addComment(Comment comment) {
        if(Objects.nonNull(comment)){
            commentMapper.addComment(comment);
        }
    }

    @Override
    public void deleteComment(Integer commentId) {
        if(Objects.nonNull(commentId)){
            commentMapper.deleteComment(commentId);
        }
    }
}

package cn.com.sharinglife.service.impl;

import cn.com.sharinglife.mapper.ArticleMapper;
import cn.com.sharinglife.pojo.Article;
import cn.com.sharinglife.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by hell on 2018/3/1
 */
@Service
public class ArticlesServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void addlike(Integer articleId) {
        if(Objects.nonNull(articleId)){
            articleMapper.addlike(articleId);
        }
    }

    @Override
    public int addArticle(Article article) {
        return articleMapper.addArticle(article);
    }

    @Override
    public Article getArticleById(Integer articleId) {
        return articleMapper.getArticleById(articleId);
    }
}

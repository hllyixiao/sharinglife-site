package cn.com.sharinglife.service.impl;

import cn.com.sharinglife.mapper.ArticleMapper;
import cn.com.sharinglife.pojo.Article;
import cn.com.sharinglife.pojo.vo.ArticleVo;
import cn.com.sharinglife.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by hell on 2018/3/1
 *
 * @author hell
 */
@Service
public class ArticlesServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void addLike(Integer articleId) {
        if (Objects.nonNull(articleId)) {
            articleMapper.addLike(articleId);
        }
    }

    @Override
    public void deleteLike(Integer articleId) {
        if (Objects.nonNull(articleId)) {
            articleMapper.deleteLike(articleId);
        }
    }

    @Override
    public void addComment(Integer articleId) {
        if (Objects.nonNull(articleId)) {
            articleMapper.addComment(articleId);
        }
    }

    @Override
    public void deleteComment(Integer articleId) {
        if (Objects.nonNull(articleId)) {
            articleMapper.deleteComment(articleId);
        }
    }

    @Override
    public int addArticle(Article article) {
        return articleMapper.addArticle(article);
    }

    @Override
    public int updateArticle(Article article) {
        return articleMapper.updateArticle(article);
    }

    @Override
    public void deleteArticleByIds(List<Integer> articleIds, int status) {
        articleMapper.deleteArticleByIds(articleIds, status);
    }

    @Override
    public void thoroughDeleteArticleByIds(List<Integer> articleIds) {
        articleMapper.thoroughDeleteArticleByIds(articleIds);
    }

    @Override
    public Article getArticleById(Integer articleId, Integer status) {
        return articleMapper.getArticleById(articleId, status);
    }

    @Override
    public PageInfo<ArticleVo> getArticlesByUserId(Integer userId, Integer status, Integer page, Integer limit) {
        //mybatis分页插件
        PageHelper.startPage(page, limit);
        List<ArticleVo> articles = articleMapper.getArticleByUserId(userId, status);
        PageInfo<ArticleVo> pageInfo = new PageInfo<>(articles);
        return pageInfo;
    }
}

package cn.com.sharinglife.service;

import cn.com.sharinglife.pojo.Article;
import cn.com.sharinglife.pojo.responsedata.ArticleResponse;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by hell on 2018/3/1
 */
public interface ArticleService {

    void addLike(Integer articleId);

    void deleteLike(Integer articleId);

    void addComment(Integer articleId);

    void deleteComment(Integer articleId);

    int addArticle(Article article);

    void deleteArticleByIds(List<Integer> articleIds,int status);

    void thoroughDeleteArticleByIds(List<Integer> articleIds);

    Article getArticleById(Integer articleId);

    PageInfo<ArticleResponse> getArticlesByUserId(Integer userId, Integer status, Integer page, Integer limit);
}

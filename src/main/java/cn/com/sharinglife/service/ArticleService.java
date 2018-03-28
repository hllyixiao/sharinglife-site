package cn.com.sharinglife.service;

import cn.com.sharinglife.pojo.Article;

/**
 * Created by hell on 2018/3/1
 */
public interface ArticleService {

    void addLike(Integer articleId);

    void deleteLike(Integer articleId);

    void addComment(Integer articleId);

    void deleteComment(Integer articleId);

    int addArticle(Article article);

    Article getArticleById(Integer articleId);
}

package cn.com.sharinglife.service;

import cn.com.sharinglife.pojo.Article;

/**
 * @author hell
 * @data 2018/3/26 11:38
 */
public interface PictureService {

    void addlike(Integer articleId);

    int addArticle(Article article);
}

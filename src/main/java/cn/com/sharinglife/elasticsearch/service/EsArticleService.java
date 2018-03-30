package cn.com.sharinglife.elasticsearch.service;

import cn.com.sharinglife.elasticsearch.pojo.EsArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author hell
 * @data 2018/3/28 18:17
 */
public interface EsArticleService {

    /**
     * 删除EsArticle
     * @param id
     */
    void removeEsArticle(String id);

    /**
     * 更新EsArticle
     * @param esArticle
     * @return
     */
    EsArticle updateEsArticle(EsArticle esArticle);

    /**
     * 根据Article的id获取EsArticle
     * @param articleId
     * @return
     */
    EsArticle getEsArticleByArticleId(Integer articleId);

    /**
     * 最新文章列表，分页
     * @param keyword
     * @param pageable
     * @return
     */
    Page<EsArticle> listNewestEsArticles(String keyword, Pageable pageable);

    /**
     * 最热文章列表，分页
     * @param keyword
     * @param pageable
     * @return
     */
    Page<EsArticle> listHotestEsArticle(String keyword, Pageable pageable);

    /**
     * 文章列表，分页
     * @param pageable
     * @return
     */
    Page<EsArticle> listEsArticle(Pageable pageable);

    /**
     * 最新前5
     * @return
     */
    List<EsArticle> listTop5NewestEsArticle();

    /**
     * 最热前5
     * @return
     */
    List<EsArticle> listTop5HotestEsArticle();
}

package cn.com.sharinglife.elasticsearch.repository;

import cn.com.sharinglife.elasticsearch.pojo.EsArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author hell
 * @data 2018/3/28 17:58
 */
public interface EsArticleRepository extends ElasticsearchRepository<EsArticle,String> {

    Page<EsArticle> findDistinctByTitleContainsOrContentTxtContainsOrTagsNameContains(String title, String contentTxt,
                                                                                      String tagsName, Pageable pageable);

    EsArticle findByArticleId(Integer articleId);
}






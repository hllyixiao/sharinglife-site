package cn.com.sharinglife.elasticsearch.service.impl;

import cn.com.sharinglife.elasticsearch.pojo.EsArticle;
import cn.com.sharinglife.elasticsearch.repository.EsArticleRepository;
import cn.com.sharinglife.elasticsearch.service.EsArticleService;
import org.elasticsearch.search.SearchParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hell
 * @data 2018/3/28 18:18
 */
@Service
public class EsArticleServiceImpl implements EsArticleService {

    @Autowired
    private EsArticleRepository esArticleRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private static final String EMPTY_KEYWORD = "";

    private static final Pageable TOP_5_PAGEABLE = new PageRequest(0, 5);

    @Override
    public void removeEsArticle(String id) {
        esArticleRepository.delete(id);
    }

    @Override
    public EsArticle updateEsArticle(EsArticle esArticle) {
        return esArticleRepository.save(esArticle);
    }

    @Override
    public EsArticle getEsArticleByArticleId(Integer articleId) {
        return esArticleRepository.findByArticleId(articleId);
    }

    @Override
    public Page<EsArticle> listNewestEsArticles(String keyword, Pageable pageable) throws SearchParseException {
        Page<EsArticle> pages = null;
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        if (pageable.getSort() == null) {
            pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
        pages = esArticleRepository.findDistinctByTitleContainsOrContentTxtContainsOrTagsNameContains(keyword, keyword, keyword, pageable);
        return null;
    }

    @Override
    public Page<EsArticle> listHotestEsArticle(String keyword, Pageable pageable) {
        Sort sort = new Sort(Sort.Direction.DESC, "readVolumes", "comments", "likes", "updateTime");
        if (pageable.getSort() == null) {
            pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
        return esArticleRepository.findDistinctByTitleContainsOrContentTxtContainsOrTagsNameContains(keyword, keyword, keyword, pageable);
    }

    @Override
    public Page<EsArticle> listEsArticle(Pageable pageable) {
        return null;
    }

    @Override
    public List<EsArticle> listTop5NewestEsArticle() {
        Page<EsArticle> pages = listNewestEsArticles(EMPTY_KEYWORD, TOP_5_PAGEABLE);
        return pages.getContent();
    }

    @Override
    public List<EsArticle> listTop5HotestEsArticle() {
        Page<EsArticle> pages = listHotestEsArticle(EMPTY_KEYWORD, TOP_5_PAGEABLE);
        return pages.getContent();
    }
}

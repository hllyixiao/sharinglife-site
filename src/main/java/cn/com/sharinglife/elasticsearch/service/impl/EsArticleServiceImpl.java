package cn.com.sharinglife.elasticsearch.service.impl;

import cn.com.sharinglife.elasticsearch.pojo.EsArticle;
import cn.com.sharinglife.elasticsearch.repository.EsArticleRepository;
import cn.com.sharinglife.elasticsearch.service.EsArticleService;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Qualifier("primaryElasticSearChClient")
    private Client primaryElasticSearChClient;

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
        pages = esArticleRepository.findDistinctByTitleContainsOrContentTxtContains(keyword, keyword, pageable);
        return null;
    }

    @Override
    public Page<EsArticle> listHotestEsArticle(String keyword, Pageable pageable) {
        Sort sort = new Sort(Sort.Direction.DESC, "readVolumes", "comments", "likes", "updateTime");
        if (pageable.getSort() == null) {
            pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        }
        return esArticleRepository.findDistinctByTitleContainsOrContentTxtContains(keyword, keyword, pageable);
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

    @Override
    public List<EsArticle> search() {
        BoolQueryBuilder mustQuery = QueryBuilders.boolQuery();
        // 添加第1条must的条件 此处为匹配所有文档
        mustQuery.must(QueryBuilders.matchAllQuery());
        //添加第2条must的条件 title为精确查询【matchPhraseQuery】
        mustQuery.must(QueryBuilders.matchPhraseQuery("title", "时间简史"));
        SearchRequestBuilder searchRequestBuilder = primaryElasticSearChClient
                .prepareSearch("index name")
                .setTypes("type name")
                .setQuery(mustQuery)
                .addHighlightedField("*")
                .setHighlighterPreTags("<高亮前缀标签>")
                .setHighlighterPostTags("<高亮后缀标签>");

        SearchResponse searchResponse = searchRequestBuilder.setFrom((8) * 2)
                .setSize(5)
                .execute()
                .actionGet();

        return null;

    }
}

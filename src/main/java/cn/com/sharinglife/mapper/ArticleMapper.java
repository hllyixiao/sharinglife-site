package cn.com.sharinglife.mapper;

import cn.com.sharinglife.pojo.Article;
import cn.com.sharinglife.pojo.responsedata.ArticleResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hell on 2018/3/1
 */
@Mapper
public interface ArticleMapper {

    /**
     * 获取文章总数
     * @param status
     * @return
     */
    Integer countArticle(@Param(value="status") Integer status);

    /**
     * 获得留言总数
     * @param status
     * @return
     */
    Integer countArticleComment(@Param(value="status")Integer status);

    /**
     * 获得浏览量总数
     * @param status
     * @return
     */
    Integer countArticleRead(@Param(value="status")Integer status);

    /**
     * 根据id获取文章信息
     * @param articleId
     * @return
     */
    Article getArticleById(@Param(value="articleId")Integer articleId);

    /**
     * 根据用户id获取显示的文章文章信息
     * @param status
     * @param userId
     * @return
     */
    List<ArticleResponse> getArticleByUserId(@Param(value="userId")Integer userId,
                                             @Param(value="status")Integer status);

    void addLike(Integer articleId);

    void deleteLike(Integer articleId);

    void addComment(Integer articleId);

    void deleteComment(Integer articleId);

    int addArticle(Article article);

    void deleteArticleByIds(@Param(value = "articleIds") List<Integer> articleIds,
                            @Param(value = "status") int status);

    void thoroughDeleteArticleByIds (@Param(value = "articleIds") List<Integer> articleIds);
}

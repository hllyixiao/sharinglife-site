package cn.com.sharinglife.elasticsearch.pojo;

import cn.com.sharinglife.pojo.Article;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hell
 * @data 2018/3/28 17:26
 */
@Document(indexName = "id", type = "article")
public class EsArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * es的主键
     */
    @Id
    private String id;
    @Field(index = FieldIndex.not_analyzed)
    private Integer articleId; //不做全文检索字段
    @Field(index = FieldIndex.not_analyzed)
    private Integer userId;

    private String title;

    /**
     * 纯文本内容
     */
    private String contentTxt;

    /**
     * 更新时间
     */
    @Field(index = FieldIndex.not_analyzed)
    private Date updateTime;

    /**
     *
     */
    private String tagsName;

    /**
     * 是否允许评论 0:否  1：是
     */
    @Field(index = FieldIndex.not_analyzed)
    private Integer allowComments;

    /**
     * 0:删除  1：草稿  2：发布
     */
    @Field(index = FieldIndex.not_analyzed)
    private Integer status;

    /**
     * 评论数
     */
    @Field(index = FieldIndex.not_analyzed)
    private Integer comments;

    /**
     * 喜欢
     */
    @Field(index = FieldIndex.not_analyzed)
    private Integer likes;

    /**
     * 阅读数量
     */
    @Field(index = FieldIndex.not_analyzed)
    private Integer readVolumes;

    /**
     * 无参构造函数设置为 protected
     */
    protected EsArticle() {
    }

    public EsArticle(Article article) {
        articleId = article.getId();
        userId = article.getUserId();
        title = article.getTitle();
        contentTxt = article.getContentTxt();
        updateTime = article.getUpdateTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentTxt() {
        return contentTxt;
    }

    public void setContentTxt(String contentTxt) {
        this.contentTxt = contentTxt;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTagsName() {
        return tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName;
    }

    public Integer getAllowComments() {
        return allowComments;
    }

    public void setAllowComments(Integer allowComments) {
        this.allowComments = allowComments;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getReadVolumes() {
        return readVolumes;
    }

    public void setReadVolumes(Integer readVolumes) {
        this.readVolumes = readVolumes;
    }
}

package cn.com.sharinglife.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * 点赞对象
 *
 * @author Created by hell on 2018/3/16
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Like {

    /**
     * Like
     */
    private Integer id;
    /**
     * 被点赞的文章id
     */
    private Integer articleId;
    /**
     * 发表文章的作者id
     */
    private Integer articleUserId;
    /**
     * 点赞人id
     */
    private Integer userId;
    /**
     * 点赞是否被删除(0:否 1:是)
     */
    private Boolean deleted;
    /**
     * 点赞时间
     */
    private Date createTime;

    public Like() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getArticleUserId() {
        return articleUserId;
    }

    public void setArticleUserId(Integer articleUserId) {
        this.articleUserId = articleUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

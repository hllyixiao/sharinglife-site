package cn.com.sharinglife.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 *
 * 评论对象
 * @author Created by hell on 2018/3/16
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {

    /**
     * comment表主键
     */
    private Integer id;

    /**
     * 上一级评论id（默认为0）
     */
    private Integer pId;

    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 发表文章的作者id
     */
    private Integer articleUserId;

    /**
     * 被回复的作者id
     */
    private Integer ownerUserId;

    /**
     * 评论者id
     */
    private Integer userId;

    /**
     * 评论者用户名
     */
    private String userName;

    /**
     * 评论者头像
     */
    private String userAvatarUrl;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论是否被删除(0:否 1:是)
     */
    private Boolean deleted;

    /**
     * 评论时间
     */
    private Date createTime;

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
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

    public Integer getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Integer ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

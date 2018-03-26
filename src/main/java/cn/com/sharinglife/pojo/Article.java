package cn.com.sharinglife.pojo;

import cn.com.sharinglife.pojo.base.ArticleBase;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * 生活录模块发表的文章
 * Created by hell on 2018/3/1
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article extends ArticleBase {

    private Integer id;

    private Integer userId;

    private String title;

    /**
     * 纯文本内容
     */
    private String contentTxt;

    /**
     * html内容
     */
    private String contentHtml;

    private Integer contentSize;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Article() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getContentSize() {
        return contentSize;
    }

    public void setContentSize(Integer contentSize) {
        this.contentSize = contentSize;
    }
}

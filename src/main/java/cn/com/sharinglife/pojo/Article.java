package cn.com.sharinglife.pojo;

import cn.com.sharinglife.pojo.base.Base;

import java.util.Date;

/**
 * 生活录模块发表的文章
 * Created by hell on 2018/3/1
 */
public class Article extends Base{

    private Integer id;

    private Integer userId;

    private String title;

    private String describes;

    private String content;

    private Date createTime;// 创建时间

    private Date updateTime;// 更新时间

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

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}

package cn.com.sharinglife.pojo;

import cn.com.sharinglife.pojo.base.ModularBase;
import cn.com.sharinglife.util.AesUtil;
import cn.com.sharinglife.util.DataTransUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @author hell
 * @data 2018/3/28 11:43
 */
public class Article extends ModularBase {

    private Integer id;

    @JsonIgnore
    private Integer userId;

    /**
     * 加密的用户Id
     */
    private String obsUserId;

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
        this.obsUserId = AesUtil.encrypt(userId.toString());
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

    public Integer getContentSize() {
        return contentSize;
    }

    public void setContentSize(Integer contentSize) {
        this.contentSize = contentSize;
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

    public String getObsUserId() {
        return obsUserId;
    }

    public void setObsUserId(String obsUserId) {
        this.obsUserId = obsUserId;
        this.userId = Integer.valueOf(AesUtil.decrypt(obsUserId));
    }
}

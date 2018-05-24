package cn.com.sharinglife.pojo;

import cn.com.fxsh.common.util.AesUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

/**
 * @author hell
 * @data 2018/3/28 11:43
 */
public class Article {

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
     * 显示的文本内容（截取前400字）
     */
    private String displayContextTxt;
    /**
     * html内容
     */
    private String contentHtml;
    private Integer contentSize;
    /**
     * 标签名称
     */
    private List<String> tagsName;
    /**
     * 标签id
     */
    private List<Integer> tagId;
    /**
     * 是否允许评论 0:否  1：是
     */
    private Integer allowComments;
    /**
     * 0:删除  1：草稿  2：发布
     */
    private Integer status;
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
        if(contentTxt != null && contentTxt.length() > 200){
            this.displayContextTxt = contentTxt.substring(200);
        } else {
            this.displayContextTxt = contentTxt;
        }
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

    public List<String> getTagsName() {
        return tagsName;
    }

    public void setTagsName(List<String> tagsName) {
        this.tagsName = tagsName;
    }

    public List<Integer> getTagId() {
        return tagId;
    }

    public void setTagId(List<Integer> tagId) {
        this.tagId = tagId;
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

    public String getDisplayContextTxt() {
        return displayContextTxt;
    }

    public void setDisplayContextTxt(String displayContextTxt) {
        this.displayContextTxt = displayContextTxt;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", userId=" + userId +
                ", obsUserId='" + obsUserId + '\'' +
                ", title='" + title + '\'' +
                ", contentTxt='" + contentTxt + '\'' +
                ", displayContextTxt='" + displayContextTxt + '\'' +
                ", contentHtml='" + contentHtml + '\'' +
                ", contentSize=" + contentSize +
                ", tagsName=" + tagsName +
                ", tagId=" + tagId +
                ", allowComments=" + allowComments +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

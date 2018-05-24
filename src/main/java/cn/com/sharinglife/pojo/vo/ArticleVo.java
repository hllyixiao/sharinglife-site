package cn.com.sharinglife.pojo.vo;

import cn.com.fxsh.common.util.AesUtil;
import cn.com.sharinglife.util.CommonUtil;
import cn.com.sharinglife.util.DataTransUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hell
 * @date 2018/4/22
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleVo implements Serializable{

    private Integer articleId;
    private String title;
    private String displayContextTxt;
    private Integer contentSize;
    private String displayContentSize;
    private String contentHtml;
    /**
     * 更新时间
     */
    @JsonIgnore
    private Date updateTime;
    private String displayUpdateTime;
    /**
     * 评论数
     */
    @JsonIgnore
    private Integer comments;
    private String displayComments;
    /**
     * 喜欢
     */
    @JsonIgnore
    private Integer likes;
    private String displayLikes;
    /**
     *  对具体的用户来说，是否点赞过该文章
     */
    private Boolean liked;
    /**
     * 阅读数量
     */
    @JsonIgnore
    private Integer readVolumes;
    private String displayReadVolumes;

    /**
     * 第一张图片
     */
    private String firstImg;
    /**
     * 是否允许评论 0:否  1：是
     */
    private boolean allowComments;
    @JsonIgnore
    private Integer userId;
    /**
     * 加密的用户Id
     */
    private String obsUserId;
    /**
     * 用户头像图片路径
     */
    private String avatarUrl;
    /**
     * 用户名称
     */
    private String userName;

    public ArticleVo() {
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayContextTxt() {
        return displayContextTxt;
    }

    public void setDisplayContextTxt(String displayContextTxt) {
        this.displayContextTxt = displayContextTxt;
    }

    public Integer getContentSize() {
        return contentSize;
    }

    public void setContentSize(Integer contentSize) {
        this.contentSize = contentSize;
        displayContentSize = DataTransUtil.numberToCompany((double) contentSize);
    }

    public String getDisplayContentSize() {
        return displayContentSize;
    }

    public void setDisplayContentSize(String displayContentSize) {
        this.displayContentSize = displayContentSize;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
        this.firstImg = CommonUtil.showFirstImgUrl(contentHtml);
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        displayUpdateTime = DataTransUtil.dateToDisplay(updateTime);
    }

    public String getDisplayUpdateTime() {
        return displayUpdateTime;
    }

    public void setDisplayUpdateTime(String displayUpdateTime) {
        this.displayUpdateTime = displayUpdateTime;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
        displayComments = DataTransUtil.numberToCompany((double) comments);
    }

    public String getDisplayComments() {
        return displayComments;
    }

    public void setDisplayComments(String displayComments) {
        this.displayComments = displayComments;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
        displayLikes = DataTransUtil.numberToCompany((double) likes);
    }

    public String getDisplayLikes() {
        return displayLikes;
    }

    public void setDisplayLikes(String displayLikes) {
        this.displayLikes = displayLikes;
    }

    public Integer getReadVolumes() {
        return readVolumes;
    }

    public void setReadVolumes(Integer readVolumes) {
        this.readVolumes = readVolumes;
        displayReadVolumes = DataTransUtil.numberToCompany((double) readVolumes);
    }

    public String getDisplayReadVolumes() {
        return displayReadVolumes;
    }

    public void setDisplayReadVolumes(String displayReadVolumes) {
        this.displayReadVolumes = displayReadVolumes;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }

    public boolean isAllowComments() {
        return allowComments;
    }

    public void setAllowComments(boolean allowComments) {
        this.allowComments = allowComments;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
        this.obsUserId = AesUtil.encrypt(userId.toString());
    }

    public String getObsUserId() {
        return obsUserId;
    }

    public void setObsUserId(String obsUserId) {
        this.obsUserId = obsUserId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    @Override
    public String toString() {
        return "ArticleVo{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", displayContextTxt='" + displayContextTxt + '\'' +
                ", contentSize=" + contentSize +
                ", displayContentSize='" + displayContentSize + '\'' +
                ", contentHtml='" + contentHtml + '\'' +
                ", updateTime=" + updateTime +
                ", displayUpdateTime='" + displayUpdateTime + '\'' +
                ", comments=" + comments +
                ", displayComments='" + displayComments + '\'' +
                ", likes=" + likes +
                ", displayLikes='" + displayLikes + '\'' +
                ", readVolumes=" + readVolumes +
                ", displayReadVolumes='" + displayReadVolumes + '\'' +
                ", firstImg='" + firstImg + '\'' +
                ", allowComments=" + allowComments +
                ", userId=" + userId +
                ", obsUserId='" + obsUserId + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", userName='" + userName + '\'' +
                ", liked='" + liked + '\'' +
                '}';
    }
}

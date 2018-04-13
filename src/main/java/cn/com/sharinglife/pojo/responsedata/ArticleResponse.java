package cn.com.sharinglife.pojo.responsedata;

import cn.com.sharinglife.util.CommonUtil;
import cn.com.sharinglife.util.DataTransUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @author hell
 * @data 2018/3/30 22:06
 */
public class ArticleResponse {

    private Integer articleId;
    private String title;
    private String displayContextTxt;
    private Integer contentSize;
    private String displayContentSize;

    @JsonIgnore
    private String contentHtml;
    /**
     * 更新时间
     */
    private Date updateTime;
    private String displayUpdateTime;
    /**
     * 评论数
     */
    private Integer comments;
    private String displayComments;
    /**
     * 喜欢
     */
    private Integer likes;
    private String displayLikes;
    /**
     * 阅读数量
     */
    private Integer readVolumes;
    private String displayReadVolumes;
    private String firstImg = "/SLFile/image/defaultpath/avatar/avatar.jpg";
    /**
     * 是否允许评论 0:否  1：是
     */
    private Integer allowComments;

    public ArticleResponse() {
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
        displayContentSize = DataTransUtil.numberToCompany((double)contentSize);
    }

    public String getDisplayContentSize() {
        return displayContentSize;
    }

    public void setDisplayContentSize(String displayContentSize) {
        this.displayContentSize = displayContentSize;
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
        displayComments = DataTransUtil.numberToCompany((double)comments);
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
        displayLikes = DataTransUtil.numberToCompany((double)likes);
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
        displayReadVolumes = DataTransUtil.numberToCompany((double)readVolumes);
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
        this.firstImg = "/SLFile/image/defaultpath/avatar/avatar.jpg";
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.firstImg = CommonUtil.showFirstImgUrl(contentHtml);
    }

    public Integer getAllowComments() {
        return allowComments;
    }

    public void setAllowComments(Integer allowComments) {
        this.allowComments = allowComments;
    }

    @Override
    public String toString() {
        return "ArticleResponse{" +
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
                '}';
    }
}

package cn.com.sharinglife.pojo.base;

/**
 * 生活录、图片、视频等模块的共同字段
 * Created by hell on 2018/3/1
 */
public class ArticleBase {


    private String tags;

    private Integer categoryId;

    /**
     * 0:删除  1：草稿  2：发布
     */
    private Integer status;

    /**
     * 评论数
     */
    private Integer comments;

    /**
     * 喜欢
     */
    private Integer likes;

    /**
     * 阅读数量
     */
    private Integer readVolumes;

    public ArticleBase() {
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer state) {
        this.status = state;
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

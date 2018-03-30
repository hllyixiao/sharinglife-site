package cn.com.sharinglife.pojo.base;

/**
 * 生活录、图片、视频等模块的共同字段
 * Created by hell on 2018/3/1
 */
public class ModularBase {


    private String tagsName;

    private Integer tagId;

    /**
     * 是否允许评论 0:否  1：是
     */
    private Integer allowComments;

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

    public ModularBase() {
    }

    public String getTagsName() {
        return tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
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

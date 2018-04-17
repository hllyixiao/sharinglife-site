package cn.com.sharinglife.pojo.vo;

/**
 * 补充用户的信息
 * @author hell
 * @date 2018/4/14
 */
public class UserInfoVo {

    /**
     * 点赞总数
     */
    private Integer likesAll;
    /**
     * 评论总数
     */
    private Integer commentsAll;
    /**
     * 阅读总数
     */
    private Integer readVolumesAll;
    /**
     * 文字总数
     */
    private Integer contentSizeAll;

    /**
     * 我关注的用户总数
     */
    private Integer myFollowersAll;

    /**
     * 关注我的用户总数
     */
    private Integer followToMeALl;

    public UserInfoVo() {
    }

    public Integer getLikesAll() {
        return likesAll;
    }

    public void setLikesAll(Integer likesAll) {
        this.likesAll = likesAll;
    }

    public Integer getCommentsAll() {
        return commentsAll;
    }

    public void setCommentsAll(Integer commentsAll) {
        this.commentsAll = commentsAll;
    }

    public Integer getReadVolumesAll() {
        return readVolumesAll;
    }

    public void setReadVolumesAll(Integer readVolumesAll) {
        this.readVolumesAll = readVolumesAll;
    }

    public Integer getContentSizeAll() {
        return contentSizeAll;
    }

    public void setContentSizeAll(Integer contentSizeAll) {
        this.contentSizeAll = contentSizeAll;
    }

    public Integer getMyFollowersAll() {
        return myFollowersAll;
    }

    public void setMyFollowersAll(Integer myFollowersAll) {
        this.myFollowersAll = myFollowersAll;
    }

    public Integer getFollowToMeALl() {
        return followToMeALl;
    }

    public void setFollowToMeALl(Integer followToMeALl) {
        this.followToMeALl = followToMeALl;
    }

    @Override
    public String toString() {
        return "UserInfoVo{" +
                "likesAll=" + likesAll +
                ", commentsAll=" + commentsAll +
                ", readVolumesAll=" + readVolumesAll +
                ", contentSizeAll=" + contentSizeAll +
                ", myFollowersAll=" + myFollowersAll +
                ", followToMeALl=" + followToMeALl +
                '}';
    }
}

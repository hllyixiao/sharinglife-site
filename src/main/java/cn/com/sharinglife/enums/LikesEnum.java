package cn.com.sharinglife.enums;

/**
 * @author hell
 * @data 2018/3/27 19:21
 */
public enum LikesEnum {
    ARTICLE_TYPE(1,"文章模块"),
    PICTURE_TYPE(2,"图片模块"),
    VIDEO_TYPE(3,"视频模块");

    private Integer typeId;

    private String typeName;

    LikesEnum(Integer typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }
}

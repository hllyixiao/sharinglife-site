package cn.com.sharinglife.enums;

/**
 * Created by hell on 2018/2/9
 *
 * @author hell
 */
public enum UserTypeEnum {

    ORDINARY_USER(1, "普通用户"),
    GOLD_MEMBER(2, "黄金会员"),
    ADMIN(3, "管理员"),
    ADMINISTRRATOR(4, "超级管理员");

    private Integer typeId;

    private String typeName;

    UserTypeEnum(Integer typeId, String typeName) {
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

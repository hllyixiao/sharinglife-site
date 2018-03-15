package cn.com.sharinglife.enums;

/**
 * Created by hell on 2018/3/15
 */
public enum LogActionEnum {

    LOGIN("登录"),
    UP_PWD("修改密码"),
    UP_INFO("修改个人信息"),
    DEL_ARTICLE("删除文章"),
    DEL_COMMENT("删除评论"),
    SPEND("花费享币");

    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    LogActionEnum(String action) {
        this.action = action;
    }
}

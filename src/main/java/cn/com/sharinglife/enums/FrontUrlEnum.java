package cn.com.sharinglife.enums;

/**
 * Created by hell on 2018/4/8
 */
public enum FrontUrlEnum {

    LOGIN_PAGE("/login.html");

    private String url;

    FrontUrlEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

package cn.com.sharinglife.enums;

/**
 * Created by hell on 2018/4/8
 *
 * @author hell
 */
public enum FrontUrlEnum {

    LOGIN_PAGE("http://172.4.88.129:4200/login");

    //LOGIN_PAGE("https://www.baidu.com/");

    private String url;

    FrontUrlEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

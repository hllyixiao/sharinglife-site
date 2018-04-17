package cn.com.sharinglife.pojo.responsedata;

import cn.com.sharinglife.pojo.User;

/**
 * Created by hell on 2018/2/27
 *
 * @author hell
 */
public class CommonResponse {

    /**
     * 返回的状态（0：失败  1：成功）
     */
    private Integer statusCode;
    /**
     * 返回信息描述
     */
    private String msg;
    private User user;

    public CommonResponse() {
    }

    public CommonResponse(Integer statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

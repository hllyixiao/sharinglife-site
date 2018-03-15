package cn.com.sharinglife.pojo;

import cn.com.sharinglife.enums.LogActionEnum;

/**
 * Created by hell on 2018/3/15
 */
public class Logs {

    private Integer id;

    /**
     * 操作用户ID
     */
    private Integer userId;

    /**
     * 操作用户名称
     */
    private String userName;

    /**
     * 操作动作名称
     */
    private String action;

    /**
     * 简单描述
     */
    private String describe;

    /**
     * true : 成功     false : 失败
     */
    private boolean status;

    /**
     * 操作ip
     */
    private String ip;

    public Logs(Integer userId, String userName, String action, String describe, String ip) {
        this.userId = userId;
        this.userName = userName;
        this.action = action;
        this.describe = describe;
        this.ip = ip;
    }

    public Logs(Integer userId, String userName, String action, String ip) {
        this.userId = userId;
        this.userName = userName;
        this.action = action;
        this.ip = ip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

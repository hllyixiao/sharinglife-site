package cn.com.sharinglife.pojo;

import cn.com.sharinglife.pojo.requestdata.RegisterRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by hell on 2018/1/31
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private  Integer id;

    private String name;

    private String password;

    private String phone;

    private String email;

    private Integer type;  //用户类型

    private Double shareCoin;  //分享币

    private String motto;  //座右铭

    private String avatarUrl; //头像图片路径

    private String lastLoginIp;  //最后登陆的ip

    private Date createTime;// 创建时间

    private Date updateTime;// 更新时间


    public User(){

    }

    public User(Integer id) {
        this.id = id;
    }

    public User(RegisterRequest registerRequest){
        name = registerRequest.getName();
        password = registerRequest.getPassword();
        phone = registerRequest.getPhone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getShareCoin() {
        return shareCoin;
    }

    public void setShareCoin(Double shareCoin) {
        this.shareCoin = shareCoin;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}

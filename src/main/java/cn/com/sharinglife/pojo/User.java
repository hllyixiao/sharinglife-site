package cn.com.sharinglife.pojo;

import cn.com.sharinglife.pojo.requestdata.RegisterRequest;
import cn.com.sharinglife.util.AesUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * @author Created by hell on 2018/1/31
 * 值为null则该字段不返回
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    /**
     * 用户Id,不会像传给前端
     */
    @JsonIgnore
    private Integer id;

    /**
     * 加密的用户Id
     */
    private String obsUserId;
    private String name;

    @JsonIgnore
    private String password;
    private String phone;
    private String email;
    /**
     * 用户类型
     */
    private Integer type;
    /**
     * 分享币
     */
    private Double shareCoin;
    /**
     * 座右铭
     */
    private String motto;
    /**
     * 头像图片路径
     */
    private String avatarUrl;
    /**
     * 最后登陆的ip
     */
    private String lastLoginIp;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public User() {

    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String password) {
        this.id = id;
        this.password = password;
    }

    public User(RegisterRequest registerRequest) {
        name = registerRequest.getName();
        password = registerRequest.getPassword();
        phone = registerRequest.getPhone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        this.obsUserId = AesUtil.encrypt(id.toString());
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

    public String getObsUserId() {
        return obsUserId;
    }

    public void setObsUserId(String obsUserId) {
        this.obsUserId = obsUserId;
        this.id = Integer.valueOf(AesUtil.decrypt(obsUserId));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", obsUserId='" + obsUserId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                ", shareCoin=" + shareCoin +
                ", motto='" + motto + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

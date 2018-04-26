package cn.com.sharinglife.pojo.requestdata;

import org.apache.commons.lang.StringUtils;

/**
 * Created by hell on 2018/2/12
 *
 * @author hell
 */
public class LoginRequest {

    /**
     * 验证码
     */
    private String verifyCode;
    /**
     * 手机号或用户名
     */
    private String phoneOrName;
    private String password;

    public LoginRequest() {
    }

    public String getPhoneOrName() {
        return phoneOrName;
    }

    public void setPhoneOrName(String phoneOrName) {
        this.phoneOrName = phoneOrName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public boolean nonNull() {
        if (StringUtils.isNotBlank(phoneOrName)
                && StringUtils.isNotBlank(password)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "verifyCode='" + verifyCode + '\'' +
                ", phoneOrName='" + phoneOrName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

package cn.com.sharinglife.pojo.data;

import java.util.Objects;

/**
 * Created by hell on 2018/2/12
 */
public class LoginData {

    private String phoneOrEmail;

    private String password;

    public LoginData() {
    }

    public String getPhoneOrEmail() {
        return phoneOrEmail;
    }

    public void setPhoneOrEmail(String phoneOrEmail) {
        this.phoneOrEmail = phoneOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean nonNull(){
        if(Objects.nonNull(phoneOrEmail)
                && Objects.nonNull(this.password)){
            return true;
        }
        return false;
    }
}

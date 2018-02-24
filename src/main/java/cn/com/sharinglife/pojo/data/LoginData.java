package cn.com.sharinglife.pojo.data;

import java.util.Objects;

/**
 * Created by hell on 2018/2/12
 */
public class LoginData {

    private String email;

    private String phone;

    private String password;

    public LoginData() {
    }

    public LoginData(String email, String phone, String password) {
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean nonNull(){
        if((Objects.nonNull(this.email) || Objects.nonNull(this.phone))
                && Objects.nonNull(this.password)){
            return true;
        }
        return false;
    }
}

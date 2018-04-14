package cn.com.sharinglife.pojo.requestdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang.StringUtils;

/**
 * @author Created by hell on 2018/2/11
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterRequest {

    private String name;
    private String password;
    private String phone;

    public RegisterRequest() {
    }

    public RegisterRequest(String name, String password, String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
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

    public boolean nonNull() {
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(password)
                && StringUtils.isNotBlank(phone)) {
            return true;
        }
        return false;
    }
}

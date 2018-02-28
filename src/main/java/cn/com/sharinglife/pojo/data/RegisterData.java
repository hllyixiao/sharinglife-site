package cn.com.sharinglife.pojo.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang.StringUtils;

/**
 * Created by hell on 2018/2/11
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterData {

    private String name;

    private String password;

    private String phone;

    public RegisterData() {
    }

    public RegisterData(String name, String password, String phone) {
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

    public boolean nonNull(){
        if(StringUtils.isNotBlank(this.name) && StringUtils.isNotBlank(this.password)
                && StringUtils.isNotBlank(this.phone)){
            return true;
        }
        return false;
    }
}

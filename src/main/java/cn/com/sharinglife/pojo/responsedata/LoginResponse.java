package cn.com.sharinglife.pojo.responsedata;

/**
 * Created by hell on 2018/2/27
 */
public class LoginResponse {

    //返回的状态（0：登陆失败  1：登陆成功）
    private Integer stateCode;
    //返回登陆失败或成功的信息
    private String msg;

    public LoginResponse(){}

    public LoginResponse(Integer stateCode, String msg) {
        this.stateCode = stateCode;
        this.msg = msg;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

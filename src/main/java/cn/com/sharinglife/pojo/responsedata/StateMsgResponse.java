package cn.com.sharinglife.pojo.responsedata;

/**
 * Created by hell on 2018/2/27
 */
public class StateMsgResponse {

    //返回的状态（0：失败  1：成功）
    private Integer stateCode;
    //返回登陆失败或成功的信息
    private String msg;

    public StateMsgResponse(){}

    public StateMsgResponse(Integer stateCode, String msg) {
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

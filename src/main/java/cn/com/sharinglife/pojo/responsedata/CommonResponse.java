package cn.com.sharinglife.pojo.responsedata;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hell on 2018/2/27
 *
 * @author hell
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> implements Serializable {

    /**
     * 返回的状态（0：失败  1：成功）
     */
    private Integer statusCode;
    /**
     * 返回信息描述
     */
    private String msg;
    /**
     * 用于返回单条数据
     */
    private T data;
    /**
     * 用于返回多条数据
     */
    private List<T> datas;

    public CommonResponse() {
    }

    public CommonResponse(Integer statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "statusCode=" + statusCode +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", datas=" + datas +
                '}';
    }
}

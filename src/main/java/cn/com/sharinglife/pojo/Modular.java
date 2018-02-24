package cn.com.sharinglife.pojo;

import java.util.Date;

/**
 * Created by hell on 2018/2/22
 */
public class Modular {

    private Integer id;

    private String name;

    private String remarks;

    private Date createTime;// 创建时间

    private Date updateTime;// 更新时间

    public Modular() {
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
}

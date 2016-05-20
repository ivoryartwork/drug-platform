package com.drug.platform.model;

import java.util.Date;

/**
 * Created by Yaochao on 2016/5/20.
 * 用户操作记录
 */
public class UserOptLog {

    private String username;

    private Date optDate;

    private String optDes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getOptDate() {
        return optDate;
    }

    public void setOptDate(Date optDate) {
        this.optDate = optDate;
    }

    public String getOptDes() {
        return optDes;
    }

    public void setOptDes(String optDes) {
        this.optDes = optDes;
    }
}

package com.drug.platform.model;

import java.io.Serializable;

/**
 * Created by Yaochao on 2016/3/3.
 */
public class SessionUser implements Serializable {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户性别
     */
    private byte sex;

    /**
     * 用户手机号
     */
    private long mobileNum;

    /**
     * 用户类型
     */
    private String userType;

    //登陆时间戳
    private long timestamp;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public long getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(long mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

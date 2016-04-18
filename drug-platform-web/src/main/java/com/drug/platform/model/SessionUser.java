package com.drug.platform.model;

import java.io.Serializable;

/**
 * Created by Yaochao on 2016/3/3.
 */
public class SessionUser implements Serializable {

    /**
     * 唯一标识用户的id
     */
    private long userid;

    /**
     * 用户对外id
     */
    private String openid;

    /**
     * 用户身份证
     */
    private String idcard;

    /**
     * idcard类型（1：身份证）
     */
    private byte cardtype;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户性别
     */
    private byte sex;

    /**
     * 用户手机号
     */
    private long mobilenum;

    /**
     * 用户头像
     */
    private String headimgurl;

    /**
     * 用户昵称
     */
    private String nickname;

    private String usertype;

    private SessionUser targetUser;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public byte getCardtype() {
        return cardtype;
    }

    public void setCardtype(byte cardtype) {
        this.cardtype = cardtype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public long getMobilenum() {
        return mobilenum;
    }

    public void setMobilenum(long mobilenum) {
        this.mobilenum = mobilenum;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public SessionUser getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(SessionUser targetUser) {
        this.targetUser = targetUser;
    }
}

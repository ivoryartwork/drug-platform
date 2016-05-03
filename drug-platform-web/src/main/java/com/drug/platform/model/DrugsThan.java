package com.drug.platform.model;

import java.util.Date;

/**
 * Created by Yaochao on 2016/4/29.
 * 药占比
 */
public class DrugsThan {

    //科室代码
    private String deptCode;

    //药品金额
    private float xTotal;

    //总金额
    private float yTotal;

    //药占比
    private int fate;

    //统计结果时间 精确到天
    private Date time;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public float getxTotal() {
        return xTotal;
    }

    public void setxTotal(float xTotal) {
        this.xTotal = xTotal;
    }

    public float getyTotal() {
        return yTotal;
    }

    public void setyTotal(float yTotal) {
        this.yTotal = yTotal;
    }

    public int getFate() {
        return fate;
    }

    public void setFate(int fate) {
        this.fate = fate;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

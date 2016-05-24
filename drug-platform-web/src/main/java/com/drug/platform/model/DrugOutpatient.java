package com.drug.platform.model;

import java.util.Date;

/**
 * Created by Yaochao on 2016/5/24.
 * 门诊次均药费
 */
public class DrugOutpatient {

    private String deptCode;

    private int visits;

    private float totalCosts;

    private String chargeType;

    private Date time;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public float getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(float totalCosts) {
        this.totalCosts = totalCosts;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

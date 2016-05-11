package com.drug.platform.model;

import java.util.Date;

/**
 * Created by Yaochao on 2016/5/11.
 */
public class ParByDoctor {

    private String deptName;

    private String doctor;

    private float total;

    private Date time;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

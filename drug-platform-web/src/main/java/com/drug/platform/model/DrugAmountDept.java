package com.drug.platform.model;

/**
 * Created by Yaochao on 2016/5/5.
 * 科室用药量统计
 */
public class DrugAmountDept extends DrugAmountGlobal {

    //科室代码
    private String deptCode;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}

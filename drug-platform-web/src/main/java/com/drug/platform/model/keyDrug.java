package com.drug.platform.model;

/**
 * Created by Yaochao on 2016/5/26.
 * 重点药品
 */
public class KeyDrug {

    private String drugCode;

    private String drugName;

    private String drugSpec;

    //住院或门诊 inp或outp
    private String type;

    //频次
    private String frequency;

    //用量
    private String dose;

    //单次用量
    private String singleDose;

    //用药途径
    private String routeOfAdmin;

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugSpec() {
        return drugSpec;
    }

    public void setDrugSpec(String drugSpec) {
        this.drugSpec = drugSpec;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getSingleDose() {
        return singleDose;
    }

    public void setSingleDose(String singleDose) {
        this.singleDose = singleDose;
    }

    public String getRouteOfAdmin() {
        return routeOfAdmin;
    }

    public void setRouteOfAdmin(String routeOfAdmin) {
        this.routeOfAdmin = routeOfAdmin;
    }
}

package com.drug.platform.model;

/**
 * Created by Yaochao on 2016/5/5.
 */
public class DrugAmountDoctor extends DrugAmountDept {

    //医生
    private String doctor;

    //用药人数
    private int patientAmount;

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public int getPatientAmount() {
        return patientAmount;
    }

    public void setPatientAmount(int patientAmount) {
        this.patientAmount = patientAmount;
    }
}

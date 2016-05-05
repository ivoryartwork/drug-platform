package com.drug.platform.model;

/**
 * Created by Yaochao on 2016/5/5.
 * 全局统计药品用量 门诊/住院
 */
public class DrugAmountGlobal extends DrugAmount {

    //门诊或住院 outp/inp
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

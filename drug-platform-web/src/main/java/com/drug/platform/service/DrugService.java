package com.drug.platform.service;

/**
 * Created by Yaochao on 2016/5/5.
 */
public interface DrugService {

    /**
     * 根据拼音头检索药品
     *
     * @return json格式
     */
    public String searchDrugsByInputCode(String inputCode);
}

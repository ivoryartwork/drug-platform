package com.drug.platform.dao;

import com.drug.platform.model.DrugNameDict;

import java.util.List;

/**
 * Created by Yaochao on 2016/5/5.
 * 药品名称字典
 */
public interface DrugNameDictDAO {

    /**
     * 根据拼音字头检索药品
     *
     * @param inputCode
     * @return
     */
    public List<DrugNameDict> searchByInputCode(String inputCode);
}

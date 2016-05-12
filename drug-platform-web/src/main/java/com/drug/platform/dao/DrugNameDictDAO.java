package com.drug.platform.dao;

import java.util.List;
import java.util.Map;

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
    public List<String> searchByInputCode(String inputCode);

    /**
     * 根据药品名称获取规格
     *
     * @param drugName
     * @return
     */
    public List<Map<String, Object>> searchSpecByName(String drugName);
}

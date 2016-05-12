package com.drug.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drug.platform.dao.DrugNameDictDAO;
import com.drug.platform.service.DrugService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Yaochao on 2016/5/5.
 */
@Service("drugService")
public class DrugServiceImpl implements DrugService {

    @Resource
    private DrugNameDictDAO drugNameDictDAO;

    /**
     * 根据拼音头检索药品
     *
     * @param inputCode
     * @return json格式
     */
    @Override
    public String searchDrugsByInputCode(String inputCode) {
        inputCode = "%" + inputCode.toUpperCase() + "%";
        List<String> drugNameDicts = drugNameDictDAO.searchByInputCode(inputCode);
        JSONArray result = new JSONArray();
        for (String drugName : drugNameDicts) {
            result.add(drugName);
        }
        return result.toJSONString();
    }

    /**
     * 根据药品名称获取规格
     *
     * @param drugName
     * @return
     */
    @Override
    public String searchSpecByName(String drugName) {
        List<Map<String, Object>> drugSpecList = drugNameDictDAO.searchSpecByName(drugName);
        JSONArray result = new JSONArray();
        for (Map<String, Object> map : drugSpecList) {
            JSONObject object = new JSONObject();
            object.put("DRUG_SPEC",map.get("DRUG_SPEC"));
            object.put("DRUG_CODE",map.get("DRUG_CODE"));
            result.add(object);
        }
        return result.toJSONString();
    }
}

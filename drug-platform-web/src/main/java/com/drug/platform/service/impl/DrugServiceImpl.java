package com.drug.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drug.platform.dao.DrugNameDictDAO;
import com.drug.platform.model.DrugNameDict;
import com.drug.platform.service.DrugService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        List<DrugNameDict> drugNameDicts = drugNameDictDAO.searchByInputCode(inputCode);
        JSONArray result = new JSONArray();
        for (DrugNameDict drugNameDict : drugNameDicts) {
            JSONObject object = new JSONObject();
            object.put("drugCode", drugNameDict.getDrugCode());
            object.put("drugName", drugNameDict.getDrugName());
            result.add(object);
        }
        return result.toJSONString();
    }
}

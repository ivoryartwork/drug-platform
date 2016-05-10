package com.drug.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drug.platform.dao.DrugAmountDAO;
import com.drug.platform.model.DrugAmountDept;
import com.drug.platform.model.DrugAmountDoctor;
import com.drug.platform.model.DrugAmountGlobal;
import com.drug.platform.model.QueryParams;
import com.drug.platform.service.DrugAmountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Yaochao on 2016/5/5.
 */
@Service("drugAmountService")
public class DrugAmountServiceImpl implements DrugAmountService {

    @Resource
    private DrugAmountDAO drugAmountDAO;

    /**
     * 保存全局统计结果
     *
     * @param drugAmountGlobals
     */
    @Override
    public void addDrugAmountGlobalBatch(List<DrugAmountGlobal> drugAmountGlobals) {
        drugAmountDAO.saveGlobal(drugAmountGlobals);
    }

    /**
     * 保存科室统计结果
     *
     * @param drugAmountDepts
     */
    @Override
    public void addDrugAmountDeptBatch(List<DrugAmountDept> drugAmountDepts) {
        drugAmountDAO.saveDept(drugAmountDepts);
    }

    /**
     * 保存医生统计结果
     *
     * @param drugAmountDoctors
     */
    @Override
    public void addDrugAmountDoctorBatch(List<DrugAmountDoctor> drugAmountDoctors) {
        drugAmountDAO.saveDoctor(drugAmountDoctors);
    }

    /**
     * 获取单个药品各科室用量排名
     *
     * @param queryParams
     * @return
     */
    @Override
    public String getDrugAmountRankByDept(QueryParams queryParams) {
        List<Map<String, Object>> mapList = drugAmountDAO.getDrugAmountRankByDept(queryParams);
        JSONArray result = new JSONArray();
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> map = mapList.get(i);
            JSONObject object = new JSONObject();
            object.put("NUM", i + 1);
            object.put("DEPT_NAME", map.get("DEPT_NAME"));
            object.put("AMOUNT", map.get("AMOUNT"));
            object.put("TOTAL", map.get("TOTAL"));
            object.put("UNITS", map.get("UNITS"));
            result.add(object);
        }
        return result.toJSONString();
    }

    /**
     * 获取单个药品各医师用量排名
     *
     * @param queryParams
     * @return
     */
    @Override
    public String getDrugAmountRankByDoctor(QueryParams queryParams) {
        List<Map<String, Object>> mapList = drugAmountDAO.getDrugAmountRankByDoctor(queryParams);
        JSONArray result = new JSONArray();
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> map = mapList.get(i);
            JSONObject object = new JSONObject();
            object.put("NUM", i + 1);
            object.put("DEPT_NAME", map.get("DEPT_NAME"));
            object.put("AMOUNT", map.get("AMOUNT"));
            object.put("TOTAL", map.get("TOTAL"));
            object.put("UNITS", map.get("UNITS"));
            object.put("DOCTOR", map.get("DOCTOR"));
            object.put("PATIENTAMOUNT", map.get("PATIENTAMOUNT"));
            result.add(object);
        }
        return result.toJSONString();
    }
}

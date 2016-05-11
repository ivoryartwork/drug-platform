package com.drug.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drug.platform.dao.DrugAmountDAO;
import com.drug.platform.model.DrugAmountDept;
import com.drug.platform.model.DrugAmountDoctor;
import com.drug.platform.model.DrugAmountGlobal;
import com.drug.platform.model.QueryParams;
import com.drug.platform.service.DrugAmountService;
import com.drug.platform.utils.Assert;
import com.drug.platform.utils.DateFormatUtils;
import com.drug.platform.utils.StaUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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

    /**
     * 统计 全院(门诊/住院) 单品种药品总量
     *
     * @param queryParams
     * @return
     */
    @Override
    public String staSingleDrugAmountGlobal(QueryParams queryParams) {
        Map<String, Object> rateMap = drugAmountDAO.staSingleDrugAmountGlobal(queryParams);
        if (Assert.notNull(rateMap)) {
            JSONObject result = new JSONObject();
            JSONObject rate = new JSONObject();
            rate.put("TOTAL", rateMap.get("TOTAL"));
            rate.put("AMOUNT", rateMap.get("AMOUNT"));
            rate.put("UNITS", rateMap.get("UNITS"));
            result.put("rate", rate);
            List<Map<String, Object>> deptDrugAmountList = drugAmountDAO.staSingleDrugAmountDeptList(queryParams);
            JSONArray deptRates = new JSONArray();
            for (int i = 0; i < deptDrugAmountList.size(); i++) {
                Map<String, Object> deptDrugAmount = deptDrugAmountList.get(i);
                JSONObject deptRate = new JSONObject();
                deptRate.put("UNITS", deptDrugAmount.get("UNITS"));
                deptRate.put("DEPTCODE", deptDrugAmount.get("DEPTCODE"));
                deptRate.put("TOTAL", deptDrugAmount.get("TOTAL"));
                deptRate.put("DEPT_NAME", deptDrugAmount.get("DEPT_NAME"));
                deptRate.put("AMOUNT", deptDrugAmount.get("AMOUNT"));
                deptRates.add(deptRate);
            }
            result.put("deptRates", deptRates);

            Date[][] dates = StaUtil.getTrendTime();
            String rateTrend = "";
            String rateTrendDate = "";
            for (int i = 0; i < dates.length; i++) {
                queryParams.setBeginDate(dates[i][0]);
                queryParams.setEndDate(dates[i][1]);
                Map<String, Object> rateMap1 = drugAmountDAO.staSingleDrugAmountGlobal(queryParams);
                rateTrendDate += "," + DateFormatUtils.format(dates[i][0], "yyyy-MM");
                if (Assert.isNull(rateMap1)) {
                    rateTrend += ",0";
                } else {
                    rateTrend += "," + rateMap1.get("TOTAL");
                }
            }
            result.put("trend", rateTrendDate.substring(1) + "#" + rateTrend.substring(1));
            return result.toJSONString();
        }
        return "";
    }
}

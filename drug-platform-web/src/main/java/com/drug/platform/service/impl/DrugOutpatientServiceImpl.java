package com.drug.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drug.platform.dao.DrugOutpatientDAO;
import com.drug.platform.model.DrugOutpatient;
import com.drug.platform.model.QueryParams;
import com.drug.platform.service.DrugOutpatientService;
import com.drug.platform.utils.Assert;
import com.drug.platform.utils.DateFormatUtils;
import com.drug.platform.utils.StaUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Yaochao on 2016/5/24.
 */
@Service("drugOutpatientService")
public class DrugOutpatientServiceImpl implements DrugOutpatientService {

    @Resource
    private DrugOutpatientDAO drugOutpatientDAO;

    /**
     * 批量添加门诊次均药费统计结果
     *
     * @param drugOutpatients
     */
    @Override
    public void addDrugOutpatientBatch(List<DrugOutpatient> drugOutpatients) {
        drugOutpatientDAO.save(drugOutpatients);
    }

    /**
     * 获取全局门诊次均药费统计结果
     *
     * @param queryParams
     * @return
     */
    @Override
    public String getDrugOutpatientGlobal(QueryParams queryParams) {
        Map<String, Object> rateMap = drugOutpatientDAO.staDrugOutpatientGlobal(queryParams);
        if (Assert.notNull(rateMap)) {
            JSONObject result = new JSONObject();
            result.put("timesDrugCost", rateMap.get("TIMESDRUGCOST"));
            result.put("targetTimesDrugCost", 4000);
            List<Map<String, Object>> deptDrugOutpatientList = drugOutpatientDAO.staDrugOutpatientByDept(queryParams);
            JSONArray timesDrugCostList = new JSONArray();
            for (int i = 0; i < deptDrugOutpatientList.size(); i++) {
                Map<String, Object> deptDrugOutpatient = deptDrugOutpatientList.get(i);
                JSONObject deptRate = new JSONObject();
                deptRate.put("timesDrugCost", deptDrugOutpatient.get("TIMESDRUGCOST"));
                deptRate.put("totalDrugCost", deptDrugOutpatient.get("TOTALCOSTS"));
                deptRate.put("deptCode", deptDrugOutpatient.get("DEPTCODE"));
                deptRate.put("deptName", deptDrugOutpatient.get("DEPT_NAME"));
                deptRate.put("times", deptDrugOutpatient.get("VISITS"));
                deptRate.put("targetTimesDrugCost", 12);
                deptRate.put("rank", i + 1);
                timesDrugCostList.add(deptRate);
            }
            result.put("timesDrugCostList", timesDrugCostList);

            Date[][] dates = StaUtil.getTrendTime(queryParams.getEndDate());
            String rateTrend = "";
            String rateTrendDate = "";
            for (int i = 0; i < dates.length; i++) {
                queryParams.setBeginDate(dates[i][0]);
                queryParams.setEndDate(dates[i][1]);
                Map<String, Object> rateMap1 = drugOutpatientDAO.staDrugOutpatientGlobal(queryParams);
                rateTrendDate += "," + DateFormatUtils.format(dates[i][0], "yyyy-MM");
                if (Assert.isNull(rateMap1)) {
                    rateTrend += ",0";
                } else {
                    rateTrend += "," + rateMap1.get("TIMESDRUGCOST");
                }
            }
            result.put("trend", rateTrendDate.substring(1) + "#" + rateTrend.substring(1));
            return result.toJSONString();
        }
        return "";
    }

    /**
     * 获取某科室次均药费统计结果
     *
     * @param queryParams
     * @return
     */
    @Override
    public String getDrugOutpatientByDept(QueryParams queryParams) {
        Date[][] dates = StaUtil.getTrendTime(queryParams.getEndDate());
        String rateTrend = "";
        String rateTrendDate = "";
        for (int i = 0; i < dates.length; i++) {
            queryParams.setBeginDate(dates[i][0]);
            queryParams.setEndDate(dates[i][1]);
            Map<String, Object> rateMap1 = drugOutpatientDAO.staDrugOutpatientGlobal(queryParams);
            rateTrendDate += "," + DateFormatUtils.format(dates[i][0], "yyyy-MM");
            if (Assert.isNull(rateMap1)) {
                rateTrend += ",0";
            } else {
                rateTrend += "," + rateMap1.get("TIMESDRUGCOST");
            }
        }
        JSONObject result = new JSONObject();
        result.put("trend", rateTrendDate.substring(1) + "#" + rateTrend.substring(1));
        return result.toJSONString();
    }
}

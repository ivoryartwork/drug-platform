package com.drug.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drug.platform.dao.AverageDrugFeeDAO;
import com.drug.platform.model.AverageDrugFee;
import com.drug.platform.model.QueryParams;
import com.drug.platform.service.AverageDrugFeeService;
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
public class AverageDrugFeeServiceImpl implements AverageDrugFeeService {

    @Resource
    private AverageDrugFeeDAO averageDrugFeeDAO;

    /**
     * 批量添加次均药费统计结果
     *
     * @param averageDrugFees
     */
    @Override
    public void addAverageDrugFeeBatch(List<AverageDrugFee> averageDrugFees) {
        averageDrugFeeDAO.save(averageDrugFees);
    }

    /**
     * 获取全局次均药费统计结果
     *
     * @param queryParams
     * @return
     */
    @Override
    public String getAverageDrugFeeGlobal(QueryParams queryParams) {
        Map<String, Object> rateMap = averageDrugFeeDAO.staAverageDrugFeeGlobal(queryParams);
        if (Assert.notNull(rateMap)) {
            JSONObject result = new JSONObject();
            result.put("timesDrugCost", rateMap.get("TIMESDRUGCOST"));
            result.put("targetTimesDrugCost", 4000);
            List<Map<String, Object>> deptDrugOutpatientList = averageDrugFeeDAO.staDrugOutpatientByDept(queryParams);
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
                Map<String, Object> rateMap1 = averageDrugFeeDAO.staAverageDrugFeeGlobal(queryParams);
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
     * 获取某科室门诊次均药费统计结果
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
            Map<String, Object> rateMap1 = averageDrugFeeDAO.staAverageDrugFeeGlobal(queryParams);
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

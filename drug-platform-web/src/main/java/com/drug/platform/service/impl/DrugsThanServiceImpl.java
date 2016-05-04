package com.drug.platform.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drug.platform.dao.DrugsThanDAO;
import com.drug.platform.model.DrugsThan;
import com.drug.platform.model.QueryParams;
import com.drug.platform.service.DrugsThanService;
import com.drug.platform.utils.Assert;
import com.drug.platform.utils.DateFormatUtils;
import com.drug.platform.utils.StaUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Yaochao on 2016/5/3.
 */
@Service("drugsThanService")
public class DrugsThanServiceImpl implements DrugsThanService {

    @Resource
    private DrugsThanDAO drugsThanDAO;

    /**
     * 批量插入药费比
     *
     * @param drugsThans
     */
    @Override
    public void addDrugsThanBatch(List<DrugsThan> drugsThans) {
        drugsThanDAO.save(drugsThans);
    }

    /**
     * 全局统计药费比
     *
     * @param queryParams
     * @return json格式
     */
    @Override
    public String staGlobalDrugsThan(QueryParams queryParams) {
        Map<String, Object> rateMap = drugsThanDAO.staDrugsThan(queryParams);
        if (Assert.notNull(rateMap)) {
            JSONObject result = new JSONObject();
            JSONObject rate = new JSONObject();
            rate.put("totalDrugCost", rateMap.get("TOTALDRUGCOST")); //总药费
            rate.put("totalTreatCost", rateMap.get("TOTALTREATCOST")); //总药费
            rate.put("totalCost", rateMap.get("TOTALCOST"));  //总费用
            rate.put("rate", rateMap.get("FATE"));  //药费比
            result.put("rate", rate);
            List<Map<String, Object>> deptDrugsThanList = drugsThanDAO.staDeptDrugsThan(queryParams);
            JSONArray deptRates = new JSONArray();
            for (int i = 0; i < deptDrugsThanList.size(); i++) {
                Map<String, Object> deptDrugsThan = deptDrugsThanList.get(i);
                JSONObject deptRate = new JSONObject();
                deptRate.put("deptName", deptDrugsThan.get("DEPT_NAME"));
                deptRate.put("deptCode", deptDrugsThan.get("DEPTCODE"));
                deptRate.put("drugCost", deptDrugsThan.get("TOTALDRUGCOST"));
                deptRate.put("treatCost", deptDrugsThan.get("TOTALTREATCOST"));
                deptRate.put("totalCost", deptDrugsThan.get("TOTALCOST"));
                deptRate.put("rate", deptDrugsThan.get("FATE"));
                deptRate.put("targetRate", "75");
                deptRate.put("rank", i + 1);
                deptRates.add(deptRate);
            }
            result.put("deptRates", deptRates);

            Date[][] dates = StaUtil.getTrendTime();
            String rateTrend = "";
            String rateTrendDate = "";
            for (int i = 0; i < dates.length; i++) {
                queryParams.setBeginDate(dates[i][0]);
                queryParams.setEndDate(dates[i][1]);
                Map<String, Object> rateMap1 = drugsThanDAO.staDrugsThan(queryParams);
                rateTrendDate += "," + DateFormatUtils.format(dates[i][0], "yyyy-MM");
                if (Assert.isNull(rateMap1)) {
                    rateTrend += ",0";
                } else {
                    rateTrend += "," + rateMap1.get("FATE");
                }
            }
            result.put("trend", rateTrendDate.substring(1) + "#" + rateTrend.substring(1));
            return result.toJSONString();
        }
        return "";
    }
}

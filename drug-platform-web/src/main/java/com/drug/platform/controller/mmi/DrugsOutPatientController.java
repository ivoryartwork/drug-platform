package com.drug.platform.controller.mmi;

import com.alibaba.fastjson.JSONObject;
import com.drug.platform.model.QueryParams;
import com.drug.platform.service.DrugOutpatientService;
import com.drug.platform.utils.DateFormatUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/4/25.
 * 门诊次均药费比接口
 */
@RestController
@RequestMapping("/mmi/drugsOutPatient")
public class DrugsOutPatientController {

    @Resource
    private DrugOutpatientService drugOutpatientService;

    /**
     * 全院门诊次均药费比
     *
     * @param beginDate
     * @param endDate
     * @param costType  费用类别
     * @param request
     * @return
     */
    @RequestMapping(value = "/global", method = RequestMethod.GET)
    public String global(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam(required = false) String costType,
                         HttpServletRequest request) throws Exception {

        QueryParams queryParams = new QueryParams();
        queryParams.setBeginDate(DateFormatUtils.parse(beginDate, "yyyy-MM-dd"));
        queryParams.setEndDate(DateFormatUtils.parse(endDate, "yyyy-MM-dd"));
        return drugOutpatientService.getDrugOutpatientGlobal(queryParams);
//        JSONObject result = new JSONObject();
//        result.put("timesDrugCost", 5000);   //次均药费
//        result.put("targetTimesDrugCost", 4000);
//        result.put("trend", "1,2,3,4,5,6");
//        JSONArray timesDrugCostList = new JSONArray();
//        JSONObject deptTimesDrugCost = new JSONObject();
//        deptTimesDrugCost.put("deptName", "肾病科");
//        deptTimesDrugCost.put("deptCode", "123445");
//        deptTimesDrugCost.put("times", 12321);
//        deptTimesDrugCost.put("totalDrugCost", 123552);
//        deptTimesDrugCost.put("timesDrugCost", 1232);
//        deptTimesDrugCost.put("targetTimesDrugCost", 75.3);
//        deptTimesDrugCost.put("rank", 75);
//        timesDrugCostList.add(deptTimesDrugCost);
//        result.put("timesDrugCostList", timesDrugCostList);
//        return result.toJSONString();
    }

    /**
     * 科室门诊次均药费比
     *
     * @param beginDate
     * @param endDate
     * @param deptCode
     * @param costType
     * @param request
     * @return
     */
    @RequestMapping(value = "/dept", method = RequestMethod.GET)
    public String dept(@RequestParam String beginDate, @RequestParam String endDate,
                       @RequestParam String deptCode, @RequestParam(required = false) String costType,
                       HttpServletRequest request) throws Exception {

        QueryParams queryParams = new QueryParams();
        queryParams.setBeginDate(DateFormatUtils.parse(beginDate, "yyyy-MM-dd"));
        queryParams.setEndDate(DateFormatUtils.parse(endDate, "yyyy-MM-dd"));
        return drugOutpatientService.getDrugOutpatientByDept(queryParams);
    }
}

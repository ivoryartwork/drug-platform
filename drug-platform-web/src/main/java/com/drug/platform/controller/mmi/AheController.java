package com.drug.platform.controller.mmi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/4/25.
 * 住院次均药费接口
 */
@RestController
@RequestMapping("/mmi/ahe")
public class AheController {

    /**
     * 住院次均药费
     *
     * @param beginDate
     * @param endDate
     * @param costType
     * @param request
     * @return
     */
    @RequestMapping(value = "/global", method = RequestMethod.GET)
    public String global(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam(required = false) String costType,
                         HttpServletRequest request) {
        JSONObject result = new JSONObject();
        result.put("timesDrugCost", 5000);   //次均药费
        result.put("targetTimesDrugCost", 4000);
        result.put("trend", "1,2,3,4,5,6");
        JSONArray timesDrugCostList = new JSONArray();
        JSONObject deptTimesDrugCost = new JSONObject();
        deptTimesDrugCost.put("deptName", "肾病科");
        deptTimesDrugCost.put("deptCode", "123445");
        deptTimesDrugCost.put("times", 12321);
        deptTimesDrugCost.put("totalDrugCost", 123552);
        deptTimesDrugCost.put("timesDrugCost", 1232);
        deptTimesDrugCost.put("targetTimesDrugCost", 75.3);
        deptTimesDrugCost.put("rank", 75);
        timesDrugCostList.add(deptTimesDrugCost);
        result.put("timesDrugCostList", timesDrugCostList);
        return result.toJSONString();
    }

    /**
     * 某科室住院次均药费
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
                       HttpServletRequest request) {
        JSONObject result = new JSONObject();
        result.put("timesDrugCost", 5200);   //次均药费
        result.put("targetTimesDrugCost", 6000);
        result.put("trend", "1,2,3,4,5,6");
        JSONArray timesDrugCostList = new JSONArray();
        JSONObject deptTimesDrugCost = new JSONObject();
        deptTimesDrugCost.put("wardName", "肾病科一区");
        deptTimesDrugCost.put("wardCode", "123445");
        deptTimesDrugCost.put("times", 12321);
        deptTimesDrugCost.put("totalDrugCost", 123552);
        deptTimesDrugCost.put("timesDrugCost", 1232);
        deptTimesDrugCost.put("targetTimesDrugCost", 75.3);
        deptTimesDrugCost.put("rank", 75);
        timesDrugCostList.add(deptTimesDrugCost);
        result.put("timesDrugCostList", timesDrugCostList);
        return result.toJSONString();
    }

    /**
     * 某病区住院次均药费
     *
     * @param beginDate
     * @param endDate
     * @param deptCode
     * @param wardCode
     * @param costType
     * @param request
     * @return
     */
    @RequestMapping(value = "/ward", method = RequestMethod.GET)
    public String ward(@RequestParam String beginDate, @RequestParam String endDate,
                       @RequestParam String deptCode, @RequestParam String wardCode, @RequestParam(required = false) String costType,
                       HttpServletRequest request) {
        JSONObject result = new JSONObject();
        result.put("trend", "1,2,3,4,5,6");
        return result.toJSONString();
    }

}

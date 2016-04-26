package com.drug.platform.controller.mmi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/4/26.
 * 急诊抗菌药物处方比例接口
 */
@RestController
@RequestMapping("/mmi/paed")
public class PaedController {

    /**
     * 全院急诊抗菌药物处方比例
     * @param beginDate
     * @param endDate
     * @param costType
     * @param request
     * @return
     */
    @RequestMapping("/global")
    public String global(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam(required = false) String costType,
                         HttpServletRequest request) {
        JSONObject result = new JSONObject();
        result.put("npep", 2312);  //急诊处方总患者数
        result.put("npepa", 213);  //急诊抗菌药物处方患者数
        result.put("paed", 25);   //急诊抗菌药物处方比例
        result.put("targetPaed", 25); //目标值
        result.put("trend", "1,2,3,4,5,6");

        JSONArray deptPaedList = new JSONArray();
        JSONObject deptPaed = new JSONObject();
        deptPaed.put("deptCode", 123);
        deptPaed.put("deptName", "骨科");
        deptPaed.put("npep", 234);
        deptPaed.put("npepa", 34);
        deptPaed.put("paed", 34);
        deptPaed.put("targetPaed", 34);
        deptPaed.put("rank", 34);
        deptPaedList.add(deptPaed);

        result.put("deptPaedList", deptPaedList);
        return result.toJSONString();
    }

    /**
     * 某科室急诊抗菌药物处方比例
     * @param beginDate
     * @param endDate
     * @param costType
     * @param deptCode
     * @param request
     * @return
     */
    @RequestMapping("/dept")
    public String dept(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam(required = false) String costType,
                       @RequestParam String deptCode, HttpServletRequest request) {
        JSONObject result = new JSONObject();
        result.put("npep", 2312);  //住院患者数
        result.put("npepa", 213);  //使用抗菌药物人数
        result.put("paed", 25);   //住院抗菌药物使用率
        result.put("targetPaed", 25); //目标值
        result.put("trend", "1,2,3,4,5,6");
        return result.toJSONString();
    }
}

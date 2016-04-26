package com.drug.platform.controller.mmi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/4/26.
 * 门诊抗菌药物处方比例接口
 */
@RestController
@RequestMapping("/mmi/poap")
public class PoapController {

    /**
     * 全院门诊抗菌药物处方比例
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
        result.put("nopp", 2312);  //门诊处方总患者数
        result.put("nopa", 213);  //门诊抗菌药物处方患者数
        result.put("poap", 25);   //门诊抗菌药物处方比例
        result.put("targetPoap", 25); //目标值
        result.put("trend", "1,2,3,4,5,6");

        JSONArray deptPoapList = new JSONArray();
        JSONObject deptPoap = new JSONObject();
        deptPoap.put("deptCode", 123);
        deptPoap.put("deptName", "骨科");
        deptPoap.put("nopp", 234);
        deptPoap.put("nopa", 34);
        deptPoap.put("poap", 34);
        deptPoap.put("targetPoap", 34);
        deptPoap.put("rank", 34);
        deptPoapList.add(deptPoap);

        result.put("deptPoapList", deptPoapList);
        return result.toJSONString();
    }

    /**
     * 某科室门诊抗菌药物处方比例
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
        result.put("nopp", 2312);  //住院患者数
        result.put("nopa", 213);  //使用抗菌药物人数
        result.put("poap", 25);   //住院抗菌药物使用率
        result.put("targetPoap", 25); //目标值
        result.put("trend", "1,2,3,4,5,6");
        return result.toJSONString();
    }
}

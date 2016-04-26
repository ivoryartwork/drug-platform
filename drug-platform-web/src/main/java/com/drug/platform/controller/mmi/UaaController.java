package com.drug.platform.controller.mmi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/4/26.
 * 住院抗菌药物使用率接口
 */
@RestController
@RequestMapping("/mmi/uaa")
public class UaaController {

    /**
     * 全院住院抗菌药物使用率
     *
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
        result.put("nhp", 2312);  //住院患者数
        result.put("nadu", 213);  //使用抗菌药物人数
        result.put("puaa", 25);   //住院抗菌药物使用率
        result.put("targetPuaa", 25); //目标值
        result.put("trend", "1,2,3,4,5,6");

        JSONArray deptUaaList = new JSONArray();
        JSONObject deptUaa = new JSONObject();
        deptUaa.put("deptCode", 123);
        deptUaa.put("deptName", "骨科");
        deptUaa.put("nhp", 234);
        deptUaa.put("nadu", 34);
        deptUaa.put("puaa", 34);
        deptUaa.put("targetPuaa", 34);
        deptUaa.put("rank", 34);
        deptUaaList.add(deptUaa);

        result.put("deptUaaList", deptUaaList);
        return result.toJSONString();
    }

    /**
     * 某科室住院抗菌药物使用率
     *
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
        result.put("nhp", 2312);  //住院患者数
        result.put("nadu", 213);  //使用抗菌药物人数
        result.put("puaa", 25);   //住院抗菌药物使用率
        result.put("targetPuaa", 25); //目标值
        result.put("trend", "1,2,3,4,5,6");

        JSONArray wardUaaList = new JSONArray();
        JSONObject wardUaa = new JSONObject();
        wardUaa.put("wardCode", 123);
        wardUaa.put("wardName", "骨科一病区");
        wardUaa.put("nhp", 234);
        wardUaa.put("nadu", 34);
        wardUaa.put("puaa", 34);
        wardUaa.put("targetPuaa", 34);
        wardUaa.put("rank", 34);
        wardUaaList.add(wardUaa);

        result.put("wardUaaList", wardUaaList);
        return result.toJSONString();
    }

    @RequestMapping("/ward")
    public String ward(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam(required = false) String costType,
                       @RequestParam String wardCode, HttpServletRequest request) {
        JSONObject result = new JSONObject();
        result.put("nhp", 2312);  //住院患者数
        result.put("nadu", 213);  //使用抗菌药物人数
        result.put("puaa", 25);   //住院抗菌药物使用率
        result.put("targetPuaa", 25); //目标值
        result.put("trend", "1,2,3,4,5,6");

        return result.toJSONString();
    }
}

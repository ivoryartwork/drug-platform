package com.drug.platform.controller.mmi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/4/26.
 * i类切口预防使用抗菌药物比例接口
 */
@RestController
@RequestMapping("/mmi/ipuaa")
public class IpuaaController {

    /**
     * 全院i类切口预防使用抗菌药物比例
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
        result.put("inp", 2312);  //I类切口手术总患者数
        result.put("ianp", 213);  //I类切口预防使用抗菌药物患者数
        result.put("ipuaa", 25);   //I类切口预防使用抗菌药物比例
        result.put("targetIpuaa", 25); //目标值
        result.put("trend", "1,2,3,4,5,6");

        JSONArray deptIpuaaList = new JSONArray();
        JSONObject deptIpuaa = new JSONObject();
        deptIpuaa.put("deptCode", 123);
        deptIpuaa.put("deptName", "骨科");
        deptIpuaa.put("inp", 234);
        deptIpuaa.put("ianp", 34);
        deptIpuaa.put("ipuaa", 34);
        deptIpuaa.put("targetIpuaa", 34);
        deptIpuaa.put("rank", 34);
        deptIpuaaList.add(deptIpuaa);

        result.put("deptIpuaaList", deptIpuaaList);
        return result.toJSONString();
    }

    /**
     * 某科室i类切口预防使用抗菌药物比例
     *
     * @param beginDate
     * @param endDate
     * @param deptCode
     * @param costType
     * @param request
     * @return
     */
    @RequestMapping(value = "/dept", method = RequestMethod.GET)
    public String dept(@RequestParam String beginDate,
                       @RequestParam String endDate, @RequestParam String deptCode, @RequestParam(required = false) String costType,
                       HttpServletRequest request) {
        JSONObject result = new JSONObject();
        result.put("inp", 2312);  //I类切口手术总患者数
        result.put("ianp", 213);  //I类切口预防使用抗菌药物患者数
        result.put("ipuaa", 25);   //I类切口预防使用抗菌药物比例
        result.put("targetIpuaa", 25); //目标值
        result.put("trend", "1,2,3,4,5,6");

        JSONArray wardIpuaaList = new JSONArray();
        JSONObject wardIpuaa = new JSONObject();
        wardIpuaa.put("wardCode", 123);
        wardIpuaa.put("wardName", "骨科一病区");
        wardIpuaa.put("inp", 234);
        wardIpuaa.put("ianp", 34);
        wardIpuaa.put("ipuaa", 34);
        wardIpuaa.put("targetIpuaa", 34);
        wardIpuaa.put("rank", 34);
        wardIpuaaList.add(wardIpuaa);

        result.put("wardIpuaaList", wardIpuaaList);
        return result.toJSONString();
    }

    /**
     * 某病区i类切口预防使用抗菌药物比例
     *
     * @param beginDate
     * @param endDate
     * @param wardCode
     * @param costType
     * @param request
     * @return
     */
    @RequestMapping(value = "/ward", method = RequestMethod.GET)
    public String ward(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam String wardCode,
                       @RequestParam(required = false) String costType,
                       HttpServletRequest request) {
        JSONObject result = new JSONObject();
        result.put("inp", 2312);  //I类切口手术总患者数
        result.put("ianp", 213);  //I类切口预防使用抗菌药物患者数
        result.put("ipuaa", 25);   //I类切口预防使用抗菌药物比例
        result.put("targetIpuaa", 25); //目标值
        result.put("trend", "1,2,3,4,5,6");
        return result.toJSONString();
    }
}

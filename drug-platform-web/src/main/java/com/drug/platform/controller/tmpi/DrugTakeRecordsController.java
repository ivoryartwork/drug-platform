package com.drug.platform.controller.tmpi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ivoryartwork on 2016/4/20.
 * 取药记录查询
 */
@RestController
@RequestMapping("/tmpi/drugTakeRecords")
public class DrugTakeRecordsController {

    /**
     * 按时间查看取药记录
     *
     * @param beginDate
     * @param endDate
     * @param citizenhood
     * @param patientId
     * @param request
     * @return
     */
    @RequestMapping("/byTime")
    public String byTime(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam String citizenhood,
                         @RequestParam String patientId, HttpServletRequest request) {
        JSONObject data = new JSONObject();
        JSONObject userInfo = new JSONObject();
        userInfo.put("name", "张三");
        userInfo.put("age", 34);
        userInfo.put("subordinateUnits", "二炮");
        userInfo.put("registrationNumber", 23);
        userInfo.put("admission", 5);
        userInfo.put("totalAmountOfDrug", 34556);
        data.put("userInfo", userInfo);

        JSONArray records = new JSONArray();
        JSONObject record = new JSONObject();
        record.put("id", 1);
        record.put("takeTime", "2014-02-09/12:26:34");
        record.put("pharmacyName", "西药房");//药房名称
        record.put("drugName", "阿莫西林");
        record.put("specifications", "0.2g沙发上");//规格
        record.put("unit", "盒");
        record.put("drugType", "抗肿瘤药物");
        record.put("deptName", "消化科");
        record.put("doctor", "张三");
        record.put("number", 4);
        record.put("ia", "265"); //计价金额
        record.put("pa", "265"); //实收金额
        records.add(record);

        data.put("records", records);
        return data.toJSONString();
    }

    /**
     * 按药品查看取药记录
     *
     * @param beginDate
     * @param endDate
     * @param citizenhood
     * @param patientId
     * @param request
     * @return
     */
    @RequestMapping("/byDrug")
    public String byDrug(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam String citizenhood,
                         @RequestParam String patientId, HttpServletRequest request) {
        JSONObject data = new JSONObject();

        JSONArray records = new JSONArray();
        JSONObject record = new JSONObject();
        record.put("id", 1);
        record.put("drugType", "抗肿瘤药物");
        record.put("drugCode", "T123255");
        record.put("drugName", "阿莫西林");
        record.put("specifications", "0.2g沙发上");//规格
        record.put("unit", "盒");
        record.put("takeTimes", "5");//取药次数
        record.put("availableDays", "6");//取药次数
        record.put("number", 4);
        record.put("ia", "265"); //计价金额
        record.put("pa", "265"); //实收金额
        records.add(record);
        data.put("records", records);
        return data.toJSONString();
    }
}

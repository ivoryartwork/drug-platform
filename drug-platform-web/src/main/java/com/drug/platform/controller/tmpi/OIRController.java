package com.drug.platform.controller.tmpi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/4/27.
 * 门诊病人住院记录
 */
@RestController
@RequestMapping("/tmpi/oir")
public class OIRController {

    @RequestMapping
    public String getRecords(@RequestParam String patientId, HttpServletRequest request) {
        JSONObject result = new JSONObject();
        result.put("clinicNum", "12222");
        result.put("patientName", "张华");
        result.put("patientAge", "26");
        result.put("citizenship", "军人");
        result.put("licenseNum", "214545665");
        result.put("nation", "汉");
        result.put("maritalStatus", "未婚");
        result.put("sex", "男");
        result.put("medicareType", "80");
        result.put("phone", "1522552333");
        JSONArray records = new JSONArray();
        JSONObject record = new JSONObject();
        record.put("num", "1");
        record.put("deptName", "骨科");
        record.put("doctor", "张三");
        record.put("admissionTime", "2015-03-01 12:12:12");
        record.put("dischargeTime", "2015-03-01 12:12:12");
        record.put("wardNum", "123");
        record.put("nurse", "lili");
        record.put("diseaseCause", "xxx");
        record.put("examResult", "xxx");
        record.put("hospiatlSuggest", "xxx");
        record.put("doctorSuggest", "xxx");
        records.add(record);
        result.put("records", records);
        return result.toJSONString();
    }
}

package com.drug.platform.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ivoryartwork on 2016/4/19.
 */
@RestController
@RequestMapping("/info")
public class BaseInfoController {

    /**
     * 根据科室编号获取相应的病区
     *
     * @param request
     * @return
     */
    @RequestMapping("/deptWards")
    public String getDeptWard(@RequestParam String deptCode, HttpServletRequest request) {
        JSONArray wards = new JSONArray();
        JSONObject ward1 = new JSONObject();
        ward1.put("wardCode", "1");
        ward1.put("wardName", "肾病一区");
        JSONObject ward2 = new JSONObject();
        ward2.put("wardCode", "2");
        ward2.put("wardName", "肾病二区");
        wards.add(ward1);
        wards.add(ward2);
        return wards.toJSONString();
    }

    /**
     * 获取所有科室编号及名称
     *
     * @param request
     * @return
     */
    @RequestMapping("/depts")
    public String getDepts(HttpServletRequest request) {
        JSONArray depts = new JSONArray();
        JSONObject dept1 = new JSONObject();
        dept1.put("deptCode", "1");
        dept1.put("deptName", "肾病科");
        JSONObject dept2 = new JSONObject();
        dept2.put("deptCode", "2");
        dept2.put("deptName", "骨科");
        depts.add(dept1);
        depts.add(dept2);
        return depts.toJSONString();
    }
}

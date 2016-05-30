package com.drug.platform.controller;

import com.alibaba.fastjson.JSONObject;
import com.drug.platform.service.KeyDrugService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/5/30.
 * 重点药品监控
 */
@RestController
@RequestMapping("/kdc")
public class KeyDrugController {

    @Resource
    private KeyDrugService keyDrugService;

    /**
     * 添加药品
     *
     * @param data
     * @param request
     * @return
     */
    @RequestMapping(value = "/addKeyDrug", method = RequestMethod.POST)
    public String addKeyDrug(@RequestParam String data, HttpServletRequest request) {
        try {
            keyDrugService.addKeyDrug(data);
            return ControllerUtil.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ControllerUtil.ERROR;
    }

    /**
     * 获取药品列表
     *
     * @param type    门诊或住院
     * @param pageNum
     * @param request
     * @return
     */
    @RequestMapping("/getKeyDrugStaInfo")
    public String getKeyDrugStaInfo(@RequestParam String type, @RequestParam int pageNum, HttpServletRequest request) {
        return keyDrugService.getKeyDrugStaInfo(type, pageNum);
    }

    /**
     * 删除重点药品
     *
     * @param drugCode
     * @param request
     * @return
     */
    @RequestMapping(value = "/deleteKeyDrug", method = RequestMethod.POST)
    public String deleteKeyDrug(@RequestParam String drugCode, HttpServletRequest request) {
        try {
            keyDrugService.deleteKeyDrug(drugCode);
            return ControllerUtil.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ControllerUtil.ERROR;
    }

    /**
     * 根据名称搜索重点药品
     *
     * @param drugName
     * @param request
     * @return
     */
    @RequestMapping(value = "/getKeyDrugStaInfoByName", method = RequestMethod.POST)
    public String getKeyDrugStaInfoByName(@RequestParam String drugName, HttpServletRequest request) {
        return keyDrugService.getKeyDrugStaInfoByName(drugName);
    }

    /**
     * 推荐同类药品列表
     *
     * @param drugCode
     * @param request
     * @return
     */
    @RequestMapping(value = "/recommendSimilarKeyDrugList", method = RequestMethod.POST)
    public String recommendSimilarKeyDrugList(@RequestParam String drugCode, HttpServletRequest request) {
        return keyDrugService.recommendSimilarKeyDrugList(drugCode);
    }
}

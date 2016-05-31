package com.drug.platform.controller;

import com.drug.platform.service.KeyDrugService;
import com.drug.platform.utils.BytesHelper;
import com.drug.platform.utils.JExcel;
import jxl.write.Label;
import jxl.write.WritableCell;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(@RequestParam String type, @RequestParam String pageNum, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(type+pageNum);
        String fileName = "thanDrugs_" + System.currentTimeMillis() + ".xls";
        Label label0 = new Label(0, 0, "科室名称");
        Label label1 = new Label(1, 0, "药费（元）");
        Label label2 = new Label(2, 0, "治疗费（元）");
        Label label3 = new Label(3, 0, "总数（元）");
        Label label4 = new Label(4, 0, "药占比（%）");
        Label label5 = new Label(5, 0, "目标值（%）");
        Label label6 = new Label(6, 0, "排名");
        List<WritableCell> cells = new ArrayList<>();
        cells.add(label0);
        cells.add(label1);
        cells.add(label2);
        cells.add(label3);
        cells.add(label4);
        cells.add(label5);
        cells.add(label6);
        JExcel.create(fileName, cells);
        response.setContentType("application/vnd.ms-excel");
        String excelFileName = "drugs.xls";
        try {
            excelFileName = URLEncoder.encode("药费比（2015-10-03至2016-11-03）.xls", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", "attachment;filename=" + excelFileName);
        try {
            OutputStream ouputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(fileName);
            byte[] bytes = BytesHelper.readFromInputStream(inputStream);
            ouputStream.write(bytes);
            ouputStream.flush();
            ouputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File thanDrugsExcel = new File(fileName);
        if (thanDrugsExcel.exists()) {
            thanDrugsExcel.delete();
        }
    }
}

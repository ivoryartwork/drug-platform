package com.drug.platform.controller.mmi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drug.platform.controller.annotation.UserOptLog;
import com.drug.platform.model.QueryParams;
import com.drug.platform.service.DrugsThanService;
import com.drug.platform.utils.DateFormatUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/4/20.
 * 药费比接口
 */
@RestController
@RequestMapping("/mmi/thanDrugs")
public class ThanDrugsController {

    @Resource
    private DrugsThanService drugsThanService;

    /**
     * 全院/门诊/急诊 药费比
     *
     * @param type      全院:1 门诊:2 住院:3
     * @param beginDate 开始时间
     * @param endDate   截止时间
     * @param costType  费用类别
     * @param request
     * @return
     */
    @UserOptLog(optDes = "用户查询全院药费比")
    @RequestMapping(value = "/global", method = RequestMethod.GET)
    public String global(@RequestParam int type, @RequestParam String beginDate,
                         @RequestParam String endDate, @RequestParam(required = false) String costType,
                         HttpServletRequest request) throws Exception {
        QueryParams queryParams = new QueryParams();
        if (type == 2) {
            //门诊药费比
            queryParams.setType("outp");
        } else if (type == 3) {
            //住院药费比
            queryParams.setType("inp");
        }
        queryParams.setBeginDate(DateFormatUtils.parse(beginDate, DateFormatUtils.FORMAT_DATE));
        queryParams.setEndDate(DateFormatUtils.parse(endDate, DateFormatUtils.FORMAT_DATE));
        return drugsThanService.staGlobalDrugsThan(queryParams);
    }

    /**
     * 科室 药费比
     *
     * @param type      全院:1 门诊:2 住院:3
     * @param beginDate 开始时间
     * @param endDate   截止时间
     * @param deptCode  科室代码
     * @param costType  费用类别
     * @param request
     * @return
     */
    @UserOptLog(optDes = "用户查询科室药费比")
    @RequestMapping(value = "/dept", method = RequestMethod.GET)
    public String dept(@RequestParam int type, @RequestParam String beginDate,
                       @RequestParam String endDate, @RequestParam String deptCode, @RequestParam(required = false) String costType,
                       HttpServletRequest request) {
        JSONObject result = new JSONObject();
        JSONObject rate = new JSONObject();
        rate.put("totalDrugCost", 282956767); //总药费
        rate.put("totalTreatCost", 282679567); //总药费
        rate.put("totalCost", 282967567);  //总费用
        rate.put("rate", 37);  //药费比
        result.put("rate", rate);

        //各科室病区列表
        JSONArray wardRates = new JSONArray();
        JSONObject wardRate = new JSONObject();
        wardRate.put("wardName", "肾病科一病区");
        wardRate.put("wardCode", "123445");
        wardRate.put("drugCost", "256");
        wardRate.put("treatCost", "123");
        wardRate.put("totalCost", "379");
        wardRate.put("rate", "76");
        wardRate.put("targetRate", "75");
        wardRate.put("rank", "75");
        wardRates.add(wardRate);
        result.put("wardRates", wardRates);

        //趋势
        result.put("trend", "1,2,3,4,5,6");
        result.put("trendOut", "1,2,3,4,5,6");
        result.put("trendHis", "1,2,3,4,5,6");
        return result.toJSONString();
    }

    /**
     * 科室 药费比
     *
     * @param type      全院:1 门诊:2 住院:3
     * @param beginDate 开始时间
     * @param endDate   截止时间
     * @param deptCode  科室代码
     * @param wardCode  科室病区代码
     * @param costType  费用类别
     * @param request
     * @return
     */
    @UserOptLog(optDes = "用户查询病区药费比")
    @RequestMapping(value = "/ward", method = RequestMethod.GET)
    public String ward(@RequestParam int type, @RequestParam String beginDate,
                       @RequestParam String endDate, @RequestParam String deptCode, @RequestParam String wardCode,
                       @RequestParam(required = false) String costType,
                       HttpServletRequest request) {
        JSONObject result = new JSONObject();
        JSONObject rate = new JSONObject();
        rate.put("totalDrugCost", 28297); //总药费
        rate.put("totalTreatCost", 28567); //总药费
        rate.put("totalCost", 282967);  //总费用
        rate.put("rate", 38);  //药费比
        result.put("rate", rate);

        //趋势
        result.put("trend", "1,2,3,4,5,6");
        return result.toJSONString();
    }
}

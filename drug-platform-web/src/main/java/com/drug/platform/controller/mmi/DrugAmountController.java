package com.drug.platform.controller.mmi;

import com.drug.platform.model.QueryParams;
import com.drug.platform.service.DrugAmountService;
import com.drug.platform.utils.DateFormatUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/5/10.
 */
@RestController
@RequestMapping("/mmi/drugAmount")
public class DrugAmountController {

    @Resource
    private DrugAmountService drugAmountService;

    @RequestMapping(value = "/rank", method = RequestMethod.POST)
    public String getDrugAmountRank(@RequestParam String drugCode, @RequestParam String drugSpec, @RequestParam String beginDate,
                                    @RequestParam String endDate, @RequestParam String rankBy, HttpServletRequest request) throws Exception {
        QueryParams queryParams = new QueryParams();
//        queryParams.setDrugCode(drugCode);
//        queryParams.setDrugSpec(drugSpec);
//        queryParams.setBeginDate(DateFormatUtils.parse(beginDate, DateFormatUtils.FORMAT_DATE));
//        queryParams.setEndDate(DateFormatUtils.parse(endDate, DateFormatUtils.FORMAT_DATE));
        queryParams.setDrugCode("3012583WE0");
        queryParams.setDrugSpec("6.5cm*10cm");
        queryParams.setBeginDate(DateFormatUtils.parse("2010-10-10", "yyyy-MM-dd"));
        queryParams.setEndDate(DateFormatUtils.parse("2016-10-10", "yyyy-MM-dd"));
        if (rankBy.equals("dept")) {
            return drugAmountService.getDrugAmountRankByDept(queryParams);
        } else if (rankBy.equals("outpDept")) {
            queryParams.setType("outp");
            return drugAmountService.getDrugAmountRankByDept(queryParams);
        }else if(rankBy.equals("doctor")){
            return drugAmountService.getDrugAmountRankByDoctor(queryParams);
        }else if(rankBy.equals("outpDoctor")){
            queryParams.setType("outp");
            return drugAmountService.getDrugAmountRankByDoctor(queryParams);
        }
        return "";
    }
}

package com.drug.platform.controller.sdm;

import com.drug.platform.service.SDMService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/5/9.
 */
@RestController
@RequestMapping("/sdm")
public class SDMController {

    @Resource
    private SDMService sdmService;

    /**
     * 在院药品总量统计
     *
     * @param request
     * @return
     */
    @RequestMapping("/ihus")
    public String drugStockInHospital(@RequestParam int page, HttpServletRequest request) {
        return sdmService.drugStockInHospital(page);
    }

    @RequestMapping(value = "/ihusDetail", method = RequestMethod.POST)
    public String drugStockInHospitalDetail(@RequestParam String drugCode, @RequestParam String drugName,
                                            @RequestParam String drugSpec, @RequestParam String firmId, HttpServletRequest request) {
        return sdmService.drugStockInHospitalDetail(drugCode, drugName, drugSpec, firmId);
    }
}

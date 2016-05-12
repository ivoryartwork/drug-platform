package com.drug.platform.controller;

import com.drug.platform.service.DrugService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/5/5.
 */
@RestController
@RequestMapping("/drug")
public class DrugController {

    @Resource
    private DrugService drugService;

    @RequestMapping(value = "/searchByInputCode", method = RequestMethod.GET)
    public String searchByInputCode(@RequestParam String inputCode, HttpServletRequest request) {
        return drugService.searchDrugsByInputCode(inputCode);
    }

    @RequestMapping(value = "/searchSpecByName", method = RequestMethod.POST)
    public String searchSpecByName(@RequestParam String drugName, HttpServletRequest request) {
        return drugService.searchSpecByName(drugName);
    }
}

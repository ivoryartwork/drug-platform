package com.drug.platform.controller.mr;

import com.drug.platform.service.ParService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/5/11.
 */
@RestController
@RequestMapping("/par")
public class ParController {

    @Resource
    private ParService parService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String list(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam int page,
                       HttpServletRequest request) {
        return parService.getParList(beginDate, endDate, page);
    }

    @RequestMapping(value = "/list/detail", method = RequestMethod.POST)
    public String listDetail(@RequestParam String visitNo, @RequestParam String rcptNo, @RequestParam String visitDate) {
        return parService.getParDetailList(visitNo, rcptNo, visitDate);
    }

    @RequestMapping(value = "/listByDoctor", method = RequestMethod.POST)
    public String listByDoctor(@RequestParam String beginDate, @RequestParam String endDate, @RequestParam int page,
                               HttpServletRequest request) {
        return parService.getParByDoctorList(beginDate, endDate, page);
    }

}

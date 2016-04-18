package com.drug.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/4/18.
 */
@Controller
@RequestMapping("/")
public class IndexPageController {

    @RequestMapping
    public String mainUI(HttpServletRequest request) {
        return "redirect:index.html";
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
//        SessionUser sessionUser = ControllerUtil.getSessionUser(request);
//        if (Assert.notNull(sessionUser)) {
//
//        }
//        return Pages.ERROR._404;
        return Pages.INDEX;
    }

    @RequestMapping("/than_drugs")
    public String thanDrugs(HttpServletRequest request) {
        return Pages.MMI.THAN_DRUGS;
    }
}

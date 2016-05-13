package com.drug.platform.controller;

import com.drug.platform.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/5/13.
 */
@RestController
@RequestMapping("/urm")
public class UserManagerController {

    @Resource
    private UserService userService;

    /**
     * 获取所有授权模块
     *
     * @param request
     * @return
     */
    @RequestMapping("/authModules")
    public String getAllAuthModules(HttpServletRequest request) {
        return userService.getAllAuthModules();
    }
}

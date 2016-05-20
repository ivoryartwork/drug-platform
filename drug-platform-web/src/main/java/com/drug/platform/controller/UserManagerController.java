package com.drug.platform.controller;

import com.drug.platform.service.UserService;
import com.drug.platform.utils.DateFormatUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;

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

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(@RequestParam String userData, HttpServletRequest request) {
        try {
            return userService.createUser(userData) + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "2";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUser(@RequestParam String username, HttpServletRequest request) {
        userService.deleteUser(username);
        return "0";
    }

    @RequestMapping(value = "/userList")
    public String userList(HttpServletRequest request) {
        return userService.userList();
    }

    @RequestMapping(value = "/userInfo")
    public String userInfo(@RequestParam String username, HttpServletRequest request) {
        return userService.userInfo(username);
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(@RequestParam String userData, HttpServletRequest request) {
        try {
            userService.updateUser(userData);
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1";
    }

    @RequestMapping("/userOptLogList")
    public String userOptLogList(String username, String beginDate, String endDate, int page, HttpServletRequest request) {
        try {
            if (beginDate == null || endDate == null) {
                return userService.userOptLogList(username, null, null, page);
            } else {
                Date begin = DateFormatUtils.parse(beginDate + " 00:00:00", DateFormatUtils.FORMAT_TIMESTAMP);
                Date end = DateFormatUtils.parse(endDate + " 23:59:59", DateFormatUtils.FORMAT_TIMESTAMP);
                return userService.userOptLogList(username, begin, end, page);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}

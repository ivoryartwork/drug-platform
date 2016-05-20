package com.drug.platform.controller;

import com.drug.platform.controller.annotation.UserOptLog;
import com.drug.platform.controller.annotation.UserType;
import com.drug.platform.model.SessionUser;
import com.drug.platform.model.User;
import com.drug.platform.service.UserService;
import com.drug.platform.utils.Assert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/3/20.
 */
@Controller
@RequestMapping
public class LoginController {

    @Resource
    private UserService userService;

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return Pages.LOGIN;
    }

    /**
     * 登录检查
     *
     * @param request
     * @return
     */
    @UserOptLog(optDes = "用户登录系统")
    @RequestMapping(value = "/login/check", method = RequestMethod.POST)
    @ResponseBody
    public String loginCheck(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        User user = userService.getByUserName(username);
        if (Assert.isNull(user)) {
            return "1";
        }
        if (!password.equals(user.getPassword())) {
            return "2";
        }
        SessionUser sessionUser = new SessionUser();
        sessionUser.setUserName(user.getUsername());
        sessionUser.setTimestamp(System.currentTimeMillis());
        sessionUser.setUserType(UserType.ADMIN.toString());
        ControllerUtil.putSessionUser(request, sessionUser);
        return "0";
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        SessionUser sessionUser = ControllerUtil.getSessionUser(request);
        if (Assert.notNull(sessionUser)) {
            request.getSession(false).invalidate();
        }
        return Pages.LOGIN;
    }
}

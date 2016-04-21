package com.drug.platform.controller;

import com.drug.platform.controller.annotation.UserType;
import com.drug.platform.model.SessionUser;
import com.drug.platform.utils.Assert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Yaochao on 2016/3/20.
 */
@Controller
@RequestMapping
public class LoginController {

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
    @RequestMapping(value = "/login/check", method = RequestMethod.POST)
    @ResponseBody
    public String loginCheck(HttpServletRequest request) {
        SessionUser sessionUser = new SessionUser();
        sessionUser.setUserName("admin");
        sessionUser.setUserType(UserType.ADMIN.toString());
        ControllerUtil.putSessionUser(request, sessionUser);
        return ControllerUtil.SUCCESS;
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

package com.drug.platform.controller;

import com.drug.platform.model.SessionUser;
import com.drug.platform.utils.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Yaochao on 2016/3/4.
 */
public class ControllerUtil {

    public static final String SESSION_USER = "session_user";

    public static final String SUCCESS = "success";

    public static final String ERROR = "error";

    public static SessionUser getSessionUser(HttpServletRequest request) {
        Object o = request.getSession().getAttribute(SESSION_USER);
        if (Assert.notNull(o)) {
            return (SessionUser) o;
        }
        return null;
    }

    public static void putSessionUser(HttpServletRequest request, SessionUser sessionUser) {
        request.getSession().setAttribute(SESSION_USER, sessionUser);
    }
}

package com.drug.platform.controller;

/**
 * Created by Yaochao on 2016/3/3.
 */
public class Pages {

    public static final String LOGIN = "login_new";

    public static final String REDIRECT_LOGIN = "redirect:/login.html";

    public static final String INDEX = "indexPage";

    /**
     * 用药指标监控页面集合
     */
    public class MMI {

        //药费比
        public static final String THAN_DRUGS = "mmi.than_drugs.page";

    }

    public class ERROR {

        public static final String _404 = "404";

        public static final String AUTHENTICATION_FAILED = "authentication_failed";
    }
}

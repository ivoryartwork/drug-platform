package com.drug.platform.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ivoryartwork on 2016/4/19.
 */
@RestController
@RequestMapping("/info")
public class BaseInfoController {

    /**
     * ���ݿ��ұ�Ż�ȡ��Ӧ�Ĳ���
     *
     * @param request
     * @return
     */
    @RequestMapping("/deptWards")
    public String getDeptWard(@RequestParam(required = true) String deptCode, HttpServletRequest request) {
        return "";
    }
}

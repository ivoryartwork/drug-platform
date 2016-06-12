package com.drug.platform.controller;

import com.drug.platform.model.SessionUser;
import com.drug.platform.utils.Assert;
import com.drug.platform.utils.BytesHelper;
import com.drug.platform.utils.JExcel;
import jxl.write.Label;
import jxl.write.WritableCell;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * @param cells
     * @param fileName
     */
    public static void exportExcel(List<WritableCell> cells, String[] header, String fileName, HttpServletResponse response) {
        for (int i = 0; i < header.length; i++) {
            Label label = new Label(i, 0, header[i]);
            cells.add(label);
        }
        String tempFile = System.currentTimeMillis() + ".xls";
        JExcel.create(tempFile, cells);
        response.setContentType("application/vnd.ms-excel");
        try {
            fileName = URLEncoder.encode(fileName + ".xls", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        try {
            OutputStream ouputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(tempFile);
            byte[] bytes = BytesHelper.readFromInputStream(inputStream);
            ouputStream.write(bytes);
            ouputStream.flush();
            ouputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File thanDrugsExcel = new File(tempFile);
        if (thanDrugsExcel.exists()) {
            thanDrugsExcel.delete();
        }
    }
}

package com.drug.platform.job;

import com.drug.platform.utils.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Yaochao on 2016/5/3.
 */
public class Test {

    public static void main(String[] args) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        System.out.println(DateFormatUtils.format(now, "yyyy-MM-dd"));
    }
}

package com.drug.platform.service.impl;

import com.drug.platform.utils.DateFormatUtils;
import com.drug.platform.utils.StaUtil;

import java.util.Date;

/**
 * Created by Yaochao on 2016/5/4.
 */
public class Test {

    public static void main(String[] args) {
        Date[][] dates = StaUtil.getTrendTime();
        for (int i = 0; i < 6; i++) {
            System.out.println(DateFormatUtils.format(dates[i][0],DateFormatUtils.FORMAT_DATE));
            System.out.println(DateFormatUtils.format(dates[i][1],DateFormatUtils.FORMAT_DATE));
        }
    }
}

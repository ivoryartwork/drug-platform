package com.drug.platform.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Yaochao on 2016/5/4.
 */
public class StaUtil {

    public static String oracleDateFormatStr = "yyyy-mm-dd hh24:mi:ss";

    public static Date[][] getTrendTime(Date date) {
        Date[][] dates = new Date[6][2];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        dates[5][1] = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        dates[5][0] = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, 0);
        dates[4][1] = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        dates[4][0] = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, 0);
        dates[3][1] = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        dates[3][0] = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, 0);
        dates[2][1] = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        dates[2][0] = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, 0);
        dates[1][1] = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        dates[1][0] = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, 0);
        dates[0][1] = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        dates[0][0] = calendar.getTime();
        return dates;
    }

    public static Date[] getMReportTime(String time) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date date = dateFormat.parse(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        Date[] dates = new Date[2];
        dates[1] = calendar.getTime();
        dates[0] = date;
        return dates;
    }
}

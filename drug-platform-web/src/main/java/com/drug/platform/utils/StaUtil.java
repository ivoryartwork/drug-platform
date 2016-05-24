package com.drug.platform.utils;

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
}

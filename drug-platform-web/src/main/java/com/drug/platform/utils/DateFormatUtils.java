package com.drug.platform.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yaochao on 2015/7/17.
 */
public class DateFormatUtils {

    public static final String FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_DATE = "yyyy-MM-dd";

    public static final String FORMAT_TIME = "HH:mm:ss";

    public static Date parse(String source, String format) throws ParseException {
        SimpleDateFormat dataFormat = new SimpleDateFormat(format);
        return dataFormat.parse(source);
    }

    public static String format(Date date, String format) {
        SimpleDateFormat dataFormat = new SimpleDateFormat(format);
        return dataFormat.format(date);
    }
}

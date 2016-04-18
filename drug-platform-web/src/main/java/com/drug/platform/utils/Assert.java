package com.drug.platform.utils;

import java.util.List;

/**
 * Created by Yaochao on 2015/6/2.
 */
public class Assert {

    public static boolean isNull(Object o) {
        if (o == null) {
            return true;
        }
        return false;
    }

    public static boolean notNull(Object o) {

        return !isNull(o);
    }

    public static boolean isEmpty(List list) {
        if (notNull(list) && list.size() > 0) {
            return false;
        }
        return true;
    }

    public static boolean notEmpty(List list) {
        if (notNull(list) && list.size() > 0) {
            return true;
        }
        return false;
    }
}

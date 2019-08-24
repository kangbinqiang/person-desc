package com.spring.utils;

public class CommonUtil {

    public static boolean isEmptyString(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isNull(Object obj) {
        if (obj == null || "".equals(obj.toString())) {
            return true;
        }
        return false;
    }
}

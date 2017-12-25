package com.rabbit.java.common.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mr.Rabbit on 2017/3/14.
 */
public class AssertUtils {


    /**
     * list判空
     *
     * @param list
     * @return
     */
    public static boolean isListEmpty(List list) {
        if (null != list && !list.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * list反向判空
     *
     * @param list
     * @return
     */
    public static boolean isListNotEmpty(List list) {
        return !isListEmpty(list);
    }

    /**
     * array判空
     *
     * @param array
     * @return
     */
    public static boolean isArrayEmpty(Object[] array) {
        if (null != array && 0 != array.length) {
            return false;
        }
        return true;
    }

    /**
     * array反向判空
     *
     * @param array
     * @return
     */
    public static boolean isArrayNotEmpty(Object[] array) {
        return !isArrayEmpty(array);
    }

    /**
     * set判空
     *
     * @param set
     * @return
     */
    public static boolean isSetEmpty(Set set) {
        if (null != set && !set.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * set反向判空
     *
     * @param set
     * @return
     */
    public static boolean isSetNotEmpty(Set set) {
        return !isSetEmpty(set);
    }

    /**
     * map判空
     *
     * @param map
     * @return
     */
    public static boolean isMapEmpty(Map map) {
        if (null != map && !map.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * map反向判空
     *
     * @param map
     * @return
     */
    public static boolean isMapNotEmpty(Map map) {
        return !isMapEmpty(map);
    }

    /**
     * String判空
     *
     * @param string
     * @return
     */
    public static boolean isStringEmpty(String string) {
        if (null == string || "".equals(string.trim())) {
            return true;
        }
        return false;
    }

    /**
     * String反向判空
     *
     * @param string
     * @return
     */
    public static boolean isStringNotEmpty(String string) {
        return !isStringEmpty(string);
    }

    /**
     * 判断Double数字是否相等
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean equalDoubles(Double a, Double b) {
        if (null == a || null == b) {
            return false;
        }
        if (0 == a.compareTo(b)) {
            return true;
        }
        return false;
    }

    /**
     * 判断Float数字是否相等
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean equalFloats(Float a, Float b) {
        if (null == a || null == b) {
            return false;
        }
        if (0 == a.compareTo(b)) {
            return true;
        }
        return false;
    }

    /**
     * 判断Integer数字是否相等
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean equalIntegers(Integer a, Integer b) {
        if (null == a || null == b) {
            return false;
        }
        if (0 == a.compareTo(b)) {
            return true;
        }
        return false;
    }
}

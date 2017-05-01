package com.findandplay.util;

/**
 * Created by hajrullinbulat on 01.05.17.
 */
public class PaginationUtil {
    public static Integer getOffset(Integer limit, Integer page) {
        return (page - 1) * limit;
    }
}

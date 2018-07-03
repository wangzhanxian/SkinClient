package com.wzx.skinmanager.viewconverts;

import java.util.HashMap;

/**
 * Created by WangZhanXian on 2018/6/27.
 */

public class ConvertM {

    private static HashMap<String, IViewConvert> converts = new HashMap<>(3);

    public static void addConvert(IViewConvert convert) {
        converts.put(convert.getClass().getSimpleName(), convert);
    }

    public static IViewConvert getConvert(String methodName) {
        return converts.get(methodName);
    }

    public static void removeConvert(String methodName) {
        converts.remove(methodName);
    }

    public static void removeConvert(IViewConvert convert) {
        converts.remove(convert.getClass().getSimpleName());
    }
}

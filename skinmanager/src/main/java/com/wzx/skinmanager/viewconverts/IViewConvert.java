package com.wzx.skinmanager.viewconverts;


/**
 * Created by WangZhanXian on 2018/6/26.
 */

public interface IViewConvert<T, V> {

    void apply(V view, T res);
}

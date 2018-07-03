package com.wzx.skinmanager.viewconverts;

import android.view.View;

/**
 * Created by WangZhanXian on 2018/6/26.
 */

public interface IViewConvert<T, V extends View> {

    void apply(V view, T res);
}

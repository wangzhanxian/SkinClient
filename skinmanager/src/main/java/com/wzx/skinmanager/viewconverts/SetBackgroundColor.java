package com.wzx.skinmanager.viewconverts;

import android.view.View;

/**
 * Created by WangZhanXian on 2018/6/26.
 */

public class SetBackgroundColor implements IViewConvert<Integer, View> {


    @Override
    public void apply(View view, Integer res) {
        view.setBackgroundColor(res);
    }
}

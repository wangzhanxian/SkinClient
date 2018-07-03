package com.wzx.skinmanager.viewconverts;

import android.widget.TextView;

/**
 * Created by WangZhanXian on 2018/6/26.
 */

public class SetTextColor implements IViewConvert<Integer, TextView> {

    @Override
    public void apply(TextView view, Integer res) {
        view.setTextColor(res);
    }
}

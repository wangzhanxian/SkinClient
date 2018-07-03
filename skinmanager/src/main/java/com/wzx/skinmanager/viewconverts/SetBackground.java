package com.wzx.skinmanager.viewconverts;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by WangZhanXian on 2018/6/26.
 */

public class SetBackground implements IViewConvert<Drawable, View> {


    @Override
    public void apply(View view, Drawable res) {
        view.setBackground(res);
    }
}

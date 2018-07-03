package com.wzx.skinmanager.viewconverts;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by WangZhanXian on 2018/6/26.
 */

public class SetImageDrawable implements IViewConvert<Drawable, ImageView> {

    @Override
    public void apply(ImageView view, Drawable res) {
        view.setImageDrawable(res);
    }
}

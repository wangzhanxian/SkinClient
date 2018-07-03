package com.wzx.skinmanager.fetchers;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Created by WangZhanXian on 2018/6/26.
 */

public class DrawableResFetcher extends ResFetcher<Drawable> {

    @Override
    protected Drawable getRes(Resources pluginRes, int pluginId) {
        return pluginRes.getDrawable(pluginId);
    }

    @Override
    public String name() {
        return FetcherType.DRAWABLE;
    }
}

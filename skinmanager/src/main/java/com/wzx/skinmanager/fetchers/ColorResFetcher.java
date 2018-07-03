package com.wzx.skinmanager.fetchers;


import android.content.res.Resources;

/**
 * Created by WangZhanXian on 2018/6/26.
 */

public class ColorResFetcher extends ResFetcher<Integer> {

    @Override
    protected Integer getRes(Resources pluginRes, int pluginId) {
        return pluginRes.getColor(pluginId);
    }

    @Override
    public String name() {
        return FetcherType.COLOR;
    }

}

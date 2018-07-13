package com.wzx.skinmanager.fetchers;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;

/**
 * Created by WangZhanXian on 2018/7/12.
 */

public class LayoutFetcher extends ResFetcher<XmlResourceParser> {
    @Override
    protected XmlResourceParser getRes(Resources pluginRes, int pluginId) {
        return pluginRes.getLayout(pluginId);
    }

    @Override
    public String name() {
        return FetcherType.LAYOUT;
    }
}

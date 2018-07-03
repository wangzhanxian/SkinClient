package com.wzx.skinmanager.fetchers;


import android.content.res.Resources;
import android.util.Log;

import com.wzx.skinmanager.ResProxy;
import com.wzx.skinmanager.ViewAttr;

/**
 * Created by WangZhanXian on 2018/6/26.
 */

public abstract class ResFetcher<T> {

    private static final String TAG = ResFetcher.class.getSimpleName();

    public T getRes(ResProxy resProxy, ViewAttr attr) {
        T pluginRes = null;
        int pluginId = resProxy.getCacheId(attr.hostResId);
        if (pluginId == 0) {
            pluginId = resProxy.getPulginId(attr);
        }
        try {
            pluginRes = getRes(resProxy.getPluginRes(), pluginId);
        } catch (Exception e) {
        }
        if (pluginRes != null) {
            resProxy.saveId(attr.hostResId, pluginId);
        } else {
            Log.e(TAG,"can not find the match res, resName="+attr.resName);
        }
        return pluginRes;
    }

    protected abstract T getRes(Resources pluginRes, int pluginId);

    public abstract @FetcherType
    String name();
}

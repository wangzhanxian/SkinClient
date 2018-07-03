package com.wzx.skinmanager;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.util.SparseArray;

/**
 * Created by WangZhanXian on 2018/6/25.
 */

public class ResProxy {

    private Context mHostContext;
    private Resources mHostRes;
    private Resources mPluginRes;
    private String mPluginPkg;
    private SparseArray<Integer> cacheIds = new SparseArray<>();

    ResProxy(Context hostContext) {
        mHostContext = hostContext;
        mHostRes = hostContext.getResources();
    }

    public void setPlugin(Resources pluginRes, String pluginPkg) {
        mPluginRes = pluginRes;
        mPluginPkg = pluginPkg;
        cacheIds.clear();
    }

    public Context getHostContext() {
        return mHostContext;
    }

    public Resources getHostRes() {
        return mHostRes;
    }

    public Resources getPluginRes() {
        return mPluginRes;
    }

    public String getPluginPkg() {
        return mPluginPkg;
    }

    public boolean isPlugin() {
        return mPluginRes != null && mPluginRes != mHostRes;
    }

    public int getCacheId(@IdRes int hostId) {
        if (!isPlugin()) {
            return hostId;
        }
        Integer integer = cacheIds.get(hostId);
        return integer != null ? integer.intValue() : 0;
    }

    public void saveId(@IdRes int hostId, int pluginId) {
        if (isPlugin()) {
            cacheIds.put(hostId, pluginId);
        }
    }

    public int getPulginId(ViewAttr attr) {
        if (!isPlugin()) {
            return attr.hostResId;
        }
        return mPluginRes.getIdentifier(attr.resName, attr.resTypeName, mPluginPkg);
    }

    public String getResourceEntryName(int resId) {
        try {
            return mHostRes.getResourceEntryName(resId);
        } catch (Resources.NotFoundException e) {
            return null;
        }
    }

    public String getResourceTypeName(int resId) {
        try {
            return mHostRes.getResourceTypeName(resId);
        } catch (Resources.NotFoundException e) {
            return null;
        }
    }
}

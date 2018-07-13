package com.wzx.skinmanager;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.MainThread;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.wzx.skinmanager.fetchers.ColorResFetcher;
import com.wzx.skinmanager.fetchers.DrawableResFetcher;
import com.wzx.skinmanager.fetchers.LayoutFetcher;
import com.wzx.skinmanager.fetchers.ResFetcher;
import com.wzx.skinmanager.fetchers.ResFetcherM;
import com.wzx.skinmanager.viewconverts.ConvertM;
import com.wzx.skinmanager.viewconverts.IViewConvert;
import com.wzx.skinmanager.viewconverts.SetBackground;
import com.wzx.skinmanager.viewconverts.SetBackgroundColor;
import com.wzx.skinmanager.viewconverts.SetImageDrawable;
import com.wzx.skinmanager.viewconverts.SetTextColor;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by WangZhanXian on 2018/6/25.
 */

public class SkinCenter {

    private static final String TAG = SkinCenter.class.getSimpleName();
    private static SkinCenter skinCenter;
    private WeakHashMap<Object, SparseArray<ViewAttr>> cacheMap = new WeakHashMap<>();
    private ResProxy mResProxy;

    private SkinCenter() {
        addDefaultFetchers();
        addDefaultConverts();
    }

    @MainThread
    public static SkinCenter get() {
        if (skinCenter == null) {
            skinCenter = new SkinCenter();
        }
        return skinCenter;
    }


    /** 初始化
     * @param context
     */
    public void init(Context context) {
        mResProxy = new ResProxy(context.getApplicationContext());
        loadPlugin(context);
    }

    /**
     * 添加默认的资源解析器
     */
    private void addDefaultFetchers() {
        addFetcher(new ColorResFetcher())
                .addFetcher(new DrawableResFetcher())
                .addFetcher(new LayoutFetcher());
    }

    public SkinCenter addFetcher(ResFetcher fetcher) {
        ResFetcherM.addFetcher(fetcher);
        return this;
    }

    /**
     * 添加默认的方法转换器
     */
    private void addDefaultConverts() {
        addConvert(new SetBackground())
                .addConvert(new SetBackgroundColor())
                .addConvert(new SetTextColor())
                .addConvert(new SetImageDrawable());
    }

    public SkinCenter addConvert(IViewConvert convert) {
        ConvertM.addConvert(convert);
        return this;
    }

    /**
     * 加载插件资源
     * @param context
     */
    private void loadPlugin(Context context) {
        String skinPath = SkinCache.getCurSkinPath(context);
        if (TextUtils.isEmpty(skinPath)){
            loadDefaultSkin(context);
        }else {
            loadSkin(context,skinPath);
        }
    }

    /**加载宿主默认的资源
     * @param context
     */
    public void loadDefaultSkin(Context context){
        SkinCache.saveSkinPath(context,"");
        if (mResProxy ==null){
            mResProxy =new ResProxy(context.getApplicationContext());
        }
        mResProxy.setPlugin(context.getResources(),context.getPackageName());
        notifySkinChanged();
    }

    /**提供外部调用加载插件资源
     * @param context
     * @param skinPath
     */
    public void loadSkin(Context context,String skinPath){
        if (TextUtils.isEmpty(skinPath) || !new File(skinPath).exists()){
            return;
        }
        String pkg = PluginResProvider.getPkg(context, skinPath);
        Resources pluginRes =PluginResProvider.getResources(context,skinPath);
        if (pkg ==null || pluginRes ==null){
            return;
        }
        SkinCache.saveSkinPath(context,skinPath);
        if (mResProxy ==null){
            mResProxy =new ResProxy(context.getApplicationContext());
        }
        mResProxy.setPlugin(pluginRes,pkg);
        notifySkinChanged();
    }

    public <V> void apply(V view, int resId, String convertName) {
        apply(view, resId, ConvertM.getConvert(convertName));
    }

    public <V> void apply(V view, int resId, IViewConvert convert) {
        if (mResProxy == null) {
            throw new RuntimeException("please call init method");
        }
        applyAndRegist(view, resId, convert);
    }

    private <T,V> void applyAndRegist(V view, int resId, IViewConvert convert) {
        SparseArray<ViewAttr> attrs = cacheMap.get(view);
        if (attrs != null) {
            ViewAttr viewAttr = attrs.get(resId);
            if (viewAttr != null) {
                viewAttr.apply(view, mResProxy);
            }
        } else {
            attrs = new SparseArray<>();
            String idName = mResProxy.getResourceEntryName(resId);
            String idType = mResProxy.getResourceTypeName(resId);
            ResFetcher<T> fetcher = ResFetcherM.getFetcher(idType);
            if (idName != null && idType != null && fetcher != null) {
                ViewAttr attr = new ViewAttr<T,V>(resId, idName, idType, convert, fetcher);
                attrs.put(resId, attr);
                cacheMap.put(view, attrs);
                attr.apply(view, mResProxy);
            } else {
                Log.e(TAG, "no match res,idName=" + idName + " ,idType=" + idType + " ,fetcher=" + fetcher);
            }
        }
    }


    private void notifySkinChanged() {
        Iterator it = cacheMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object view = entry.getKey();
            SparseArray<ViewAttr> attrs = (SparseArray<ViewAttr>) entry.getValue();
            for (int i = 0; i < attrs.size(); i++) {
                attrs.valueAt(i).apply(view, mResProxy);
            }
        }
    }
}

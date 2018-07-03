package com.wzx.skinmanager;

import android.util.Log;
import android.view.View;

import com.wzx.skinmanager.fetchers.ResFetcher;
import com.wzx.skinmanager.viewconverts.IViewConvert;

/**
 * Created by WangZhanXian on 2018/6/25.
 */

public class ViewAttr<T, V extends View> {
    private static final String TAG = ViewAttr.class.getSimpleName();
    /***
     * 宿主中的资源真实id
     */
    public int hostResId;
    /***
     * 属性值refrence id对应的名称，如R.color.XX，则此值为"XX"
     */
    public String resName;
    /***
     * 属性值refrence id对应的类型，如R.color.XX，则此值为color
     */
    public String resTypeName;

    public IViewConvert<T, V> convert;

    public ResFetcher<T> fetcher;

    public ViewAttr(int hostResId, String resName, String resTypeName, IViewConvert<T, V> convert, ResFetcher<T> fetcher) {
        this.hostResId = hostResId;
        this.resName = resName;
        this.resTypeName = resTypeName;
        this.convert = convert;
        this.fetcher = fetcher;
    }

    public void apply(V view, ResProxy resProxy) {
        if (convert != null) {
            convert.apply(view, fetcher.getRes(resProxy, this));
        } else {
            Log.e(TAG, "miss the match IVewConvert");
        }
    }
}

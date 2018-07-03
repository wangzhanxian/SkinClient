package com.wzx.skinmanager.fetchers;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by WangZhanXian on 2018/6/27.
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef({FetcherType.COLOR, FetcherType.DRAWABLE, FetcherType.STRING,FetcherType.MENU,FetcherType.RAW,
        FetcherType.ARRAY, FetcherType.ANIM, FetcherType.DIMEN, FetcherType.LAYOUT,FetcherType.MIPMAP})
public @interface FetcherType {

    String COLOR = "color", DRAWABLE = "drawable", STRING = "string",MENU = "menu",RAW ="raw",
            ARRAY = "array", ANIM = "anim", DIMEN = "dimen", LAYOUT = "layout",MIPMAP = "mipmap";
}

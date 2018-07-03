package com.wzx.skinmanager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by WangZhanXian on 2018/6/26.
 */

public class SkinCache {

    private static final String SKIN_CACHE = "skin_cache";
    private static final String CUR_SKIN_PATH = "cur_skin_path";

    public static String getCurSkinPath(Context context){
        SharedPreferences settings = context.getSharedPreferences(SKIN_CACHE, Context.MODE_PRIVATE);
        return settings.getString(CUR_SKIN_PATH, "");
    }

    public static void saveSkinPath(Context context,String skinPath){
        SharedPreferences settings = context.getSharedPreferences(SKIN_CACHE, Context.MODE_PRIVATE);
        settings.edit().putString(CUR_SKIN_PATH,skinPath).commit();
    }
}

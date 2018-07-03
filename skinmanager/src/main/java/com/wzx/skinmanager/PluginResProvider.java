package com.wzx.skinmanager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.Method;

/**
 * Created by WangZhanXian on 2018/6/28.
 */

public class PluginResProvider {

    public static String getPkg(Context context,String skinApkPath){
        PackageInfo packageInfo = context.getPackageManager().getPackageArchiveInfo(skinApkPath, PackageManager.GET_UNINSTALLED_PACKAGES);
        return packageInfo !=null? packageInfo.packageName : null;
    }

    public static Resources getResources(Context context,String skinApkPath){
        AssetManager assetManager =getAssetManager(skinApkPath);
        if (assetManager !=null) {
            Resources hostRes = context.getResources();
            return new Resources(assetManager,hostRes.getDisplayMetrics(),hostRes.getConfiguration());
        }else {
            return null;
        }
    }

    public static AssetManager getAssetManager(String skinApkPath){
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, skinApkPath);
            return assetManager;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

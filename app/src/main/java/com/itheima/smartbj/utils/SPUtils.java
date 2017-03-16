package com.itheima.smartbj.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by HuangBin on 2017/3/16 21:14.
 */

public class SPUtils {

    private static final String FILE_NAME = "smartBJ";

    private static SharedPreferences getSharedPreference(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp;
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = getSharedPreference(context);
        sp.edit().putBoolean(key, value).commit();
    }

    public static  boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = getSharedPreference(context);
        return sp.getBoolean(key, defValue);
    }

}

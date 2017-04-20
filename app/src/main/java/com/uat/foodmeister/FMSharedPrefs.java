package com.uat.foodmeister;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rayhardi on 3/17/2017.
 */

public class FMSharedPrefs
{
    private static String SHARED_PREFS_FILE_NAME = "fm_sharedprefs";


    private static SharedPreferences getPrefs (Context context)
    {
        return context.getSharedPreferences(SHARED_PREFS_FILE_NAME,Context.MODE_PRIVATE);
    }

    public static void save(Context context, String key, String value)
    {

        getPrefs(context).edit().putString(key,value).commit();
    }

    public static String getString(Context context, String key, String defaultString)
    {
        return getPrefs(context).getString(key,defaultString);
    }

    public static void deleteEverything(Context context, String key)
    {

        getPrefs(context).edit().remove(key).commit();
    }
}


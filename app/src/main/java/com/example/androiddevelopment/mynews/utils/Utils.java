package com.example.androiddevelopment.mynews.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by androiddevelopment on 20.3.18..
 */

public class Utils {

    /**
     * Get shared preferences from preference xml
     *
     */

    public static boolean isShowToast(Context c){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(c);
        return  sharedPref.getBoolean("pref_toast", false);
    }

}

package com.baseproject.utils;

import android.content.SharedPreferences;

import com.baseproject.activity.BaseActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by anshul on 27/2/17.
 */

public class PrefStore {

    private static final String MY_PREFS_NAME = "creative_solutions";
    private BaseActivity baseActivity;

    public PrefStore(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public void saveString(String name, String value) {
        SharedPreferences.Editor editor = baseActivity.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(name, value);
        editor.commit();
    }

    public void saveInt(String name, int value) {
        SharedPreferences.Editor editor = baseActivity.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(name, value);
        editor.commit();
    }

    public void saveBoolean(String name, boolean value) {
        SharedPreferences.Editor editor = baseActivity.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(name, value);
        editor.commit();
    }

    public String getString(String name, String defaultValue) {
        SharedPreferences prefs = baseActivity.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        return prefs.getString(name, defaultValue);
    }

    public int getInt(String name) {
        SharedPreferences prefs = baseActivity.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        return prefs.getInt(name, 0);
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        SharedPreferences prefs = baseActivity.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        return prefs.getBoolean(name, defaultValue);
    }

}

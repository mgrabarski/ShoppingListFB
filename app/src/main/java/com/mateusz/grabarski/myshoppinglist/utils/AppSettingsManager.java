package com.mateusz.grabarski.myshoppinglist.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by MGrabarski on 01.03.2018.
 */

public class AppSettingsManager {

    // shared preferences keys
    private static final String KEY_CURRENT_LOGIN_EMAIL = "CURRENT_LOGIN_EMAIL";

    private static final String PREFERENCES_NAME = "APP_SETTINGS";

    private SharedPreferences sharedPreferences;

    public AppSettingsManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    private void setStringPrefs(String key, String value) {
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    private String getStringPrefs(String key) {
        return sharedPreferences.getString(key, null);
    }

    private void setIntPrefs(String key, int value) {
        Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    private int getIntPrefs(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    private void setBooleanPrefs(String key, boolean value) {
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    private boolean getBooleanPrefs(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    private void setLongPrefs(String key, long value) {
        Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    private long getLongPrefs(String key) {
        return sharedPreferences.getLong(key, 0L);
    }

    private void setFloatPrefs(String key, float value) {
        Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    private float getFloatPrefs(String key) {
        return sharedPreferences.getFloat(key, 0.0F);
    }

    private void clearPrefs(String key) {
        Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public void clearPrefs() {
        Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void setCurrentLoginUserEmail(String email) {
        setStringPrefs(KEY_CURRENT_LOGIN_EMAIL, email);
    }

    public String getCurrentLoginUserEmail() {
        return getStringPrefs(KEY_CURRENT_LOGIN_EMAIL);
    }
}

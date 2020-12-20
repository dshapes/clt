package com.techmate.woocommerce.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    public static String KEY_USERID = "userid";
    public static String KEY_USER_NAME = "userName";
    public static String KEY_USER_TYPE = "userType";
    public static String KEY_RECEIVE_NOTIFICATION = "push_notification";
    public static final String FCM_REGISTER_ID = "fcm_register_id";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public PrefManager(Context context){
        if (context != null){
            sharedPreferences = context.getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    public void setString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void putInteger(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void deleteUserPrefData(){
        editor.putString(PrefManager.KEY_USERID, "");
        editor.putString(PrefManager.KEY_USER_TYPE, "");
        editor.putString(PrefManager.KEY_USER_NAME, "");
        editor.putString(PrefManager.KEY_RECEIVE_NOTIFICATION, "");
        editor.commit();
    }

    public int getInteger(String key, int value) {
        return sharedPreferences.getInt(key,value);
    }

}

package com.example.sigaamobile.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SigaaSharedPreferences {
    private SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private static final String PREFERENCES_NAME = "SIGAA";

    public SigaaSharedPreferences(Activity activity){
        SharedPreferences sharedpreferences = activity.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        this.editor = sharedpreferences.edit();
    }

    public void setString(String nomeAtributo, String value){
        this.editor.putString(nomeAtributo, value);
        this.editor.apply();
    }

    public String getString(String nomeAtributo) {
        return this.sharedPreferences.getString(nomeAtributo, "");
    }

    public void setInt(String nomeAtributo, int value){
        this.editor.putInt(nomeAtributo, value);
        this.editor.apply();
    }

    public int getInt(String nomeAtributo) {
        return this.sharedPreferences.getInt(nomeAtributo, 0);
    }

    @SuppressLint("CommitPrefEdits")
    public static void clearSharedPreferences(Activity activity){
        SharedPreferences sharedpreferences = activity.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        sharedpreferences.edit().clear();
        sharedpreferences.edit().apply();
    }
}

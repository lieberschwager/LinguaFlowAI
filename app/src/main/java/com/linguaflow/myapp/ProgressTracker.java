package com.linguaflow.myapp;

import android.content.Context;
import android.content.SharedPreferences;

public class ProgressTracker {

    private static final String PREF_NAME = "progress";

    public static void incrementProgress(Context context, String english) {
        if (english == null || english.trim().isEmpty()) return;
        String lang = LanguageFetcher.getActiveLanguage(context);
        String key = english.trim() + "_" + lang;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int current = prefs.getInt(key, 0);
        prefs.edit().putInt(key, current + 1).apply();
    }

    public static int getProgress(Context context, String english) {
        if (english == null || english.trim().isEmpty()) return 0;
        String lang = LanguageFetcher.getActiveLanguage(context);
        String key = english.trim() + "_" + lang;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(key, 0);
    }

    public static void resetProgress(Context context, String english) {
        if (english == null || english.trim().isEmpty()) return;
        String lang = LanguageFetcher.getActiveLanguage(context);
        String key = english.trim() + "_" + lang;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().remove(key).apply();
    }

    public static void clearAll(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }
}
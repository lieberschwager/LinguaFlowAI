package com.linguaflow.myapp;

import android.content.Context;
import android.content.SharedPreferences;

public class VocabularyCache {

    private static final String PREF_NAME = "vocabulary";

    public static void saveTranslation(Context context, String english, String translated) {
        if (english == null || translated == null || english.trim().isEmpty() || translated.trim().isEmpty()) return;
        String lang = LanguageFetcher.getActiveLanguage(context);
        String key = english.trim() + "_" + lang;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(key, translated.trim()).apply();
    }

    public static String getGerman(Context context, String english) {
        if (english == null || english.trim().isEmpty()) return null;
        String lang = LanguageFetcher.getActiveLanguage(context);
        String key = english.trim() + "_" + lang;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, null);
    }

    public static void clearAll(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }
}
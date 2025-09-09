package com.linguaflow.myapp;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;

public class FavoritesManager {

    private static final String PREF_NAME = "favorites";

    public static void addFavorite(Context context, String english) {
        if (english == null || english.trim().isEmpty()) return;
        String lang = LanguageFetcher.getActiveLanguage(context);
        String key = "fav_" + lang;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Set<String> current = prefs.getStringSet(key, new HashSet<>());
        current.add(english.trim());
        prefs.edit().putStringSet(key, current).apply();
    }

    public static void removeFavorite(Context context, String english) {
        if (english == null || english.trim().isEmpty()) return;
        String lang = LanguageFetcher.getActiveLanguage(context);
        String key = "fav_" + lang;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Set<String> current = prefs.getStringSet(key, new HashSet<>());
        current.remove(english.trim());
        prefs.edit().putStringSet(key, current).apply();
    }

    public static Set<String> getAllFavorites(Context context) {
        String lang = LanguageFetcher.getActiveLanguage(context);
        String key = "fav_" + lang;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getStringSet(key, new HashSet<>());
    }

    public static void clearFavorites(Context context) {
        String lang = LanguageFetcher.getActiveLanguage(context);
        String key = "fav_" + lang;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().remove(key).apply();
    }
}
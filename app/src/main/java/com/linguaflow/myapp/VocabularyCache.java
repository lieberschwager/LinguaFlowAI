package com.linguaflow.myapp;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;

/**
 * Lokaler Cache für Vokabeln und Übersetzungen.
 * Verhindert doppelte API-Abfragen und ermöglicht Offline-Nutzung.
 */
public class VocabularyCache {

    private static final String PREF_NAME = "vocabulary_cache";

    public static void save(Context context, String english, String german, String example) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(english + "_de", german);
        editor.putString(english + "_ex", example);
        editor.apply();
    }

    public static boolean contains(Context context, String english) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.contains(english + "_de") && prefs.contains(english + "_ex");
    }

    public static Map<String, String> load(Context context, String english) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Map<String, String> result = new HashMap<>();
        result.put("german", prefs.getString(english + "_de", ""));
        result.put("example", prefs.getString(english + "_ex", ""));
        return result;
    }
}
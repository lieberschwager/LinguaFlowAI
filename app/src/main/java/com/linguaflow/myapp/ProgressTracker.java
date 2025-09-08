package com.linguaflow.myapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Verfolgt den Lernfortschritt pro Vokabel.
 * Speichert Wiederholungsanzahl und letzten Zeitpunkt.
 */
public class ProgressTracker {

    private static final String PREF_NAME = "progress_tracker";

    public static void increment(Context context, String word) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        int count = prefs.getInt(word + "_count", 0) + 1;
        long timestamp = System.currentTimeMillis();

        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(word + "_count", count);
        editor.putLong(word + "_last", timestamp);
        editor.apply();
    }

    public static int getCount(Context context, String word) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(word + "_count", 0);
    }

    public static long getLastTimestamp(Context context, String word) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getLong(word + "_last", 0);
    }

    public static boolean isMastered(Context context, String word) {
        int count = getCount(context, word);
        return count >= 5; // Schwelle für „gelernt“
    }
}
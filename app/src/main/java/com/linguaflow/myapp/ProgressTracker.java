package com.linguaflow.myapp;

import android.content.Context;
import android.content.SharedPreferences;

public class ProgressTracker {

    public static int getCount(Context context, String word) {
        SharedPreferences prefs = context.getSharedPreferences("progress", Context.MODE_PRIVATE);
        return prefs.getInt(word + "_count", 0);
    }

    public static void set(Context context, String word, int repetitions, long lastReviewed) {
        SharedPreferences prefs = context.getSharedPreferences("progress", Context.MODE_PRIVATE);
        prefs.edit()
            .putInt(word + "_count", repetitions)
            .putLong(word + "_last", lastReviewed)
            .apply();
    }

    public static long getLastTimestamp(Context context, String word) {
        SharedPreferences prefs = context.getSharedPreferences("progress", Context.MODE_PRIVATE);
        return prefs.getLong(word + "_last", 0);
    }

    public static boolean isMastered(Context context, String word) {
        return getCount(context, word) >= 5;
    }
}
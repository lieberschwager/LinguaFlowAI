package com.linguaflow.myapp;

import android.content.Context;
import android.content.SharedPreferences;

public class ExampleGenerator {

    private static final String PREF_NAME = "examples";

    public static void saveExample(Context context, String word, String example) {
        if (word == null || example == null || word.trim().isEmpty() || example.trim().isEmpty()) return;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(word.trim(), example.trim()).apply();
    }

    public static String getExample(Context context, String word) {
        if (word == null || word.trim().isEmpty()) return null;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(word.trim(), null);
    }

    public static String generateFallback(String english, String german) {
        return "I use " + english + " → Ich benutze " + german;
    }

    public static void clearExamples(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
    }
}
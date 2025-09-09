package com.linguaflow.myapp;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Locale;

public class LanguageFetcher {

    private static final String PREF_NAME = "language";
    private static final String KEY = "active";

    public static String getActiveLanguage(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String stored = prefs.getString(KEY, null);
        if (stored != null && !stored.trim().isEmpty()) {
            return stored.trim();
        }
        // Fallback: Systemsprache
        return Locale.getDefault().getLanguage(); // z. B. "de", "fr", "en"
    }

    public static void setActiveLanguage(Context context, String langCode) {
        if (langCode == null || langCode.trim().isEmpty()) return;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY, langCode.trim()).apply();
    }

    public static void resetLanguage(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().remove(KEY).apply();
    }
}
package com.linguaflow.myapp;

import android.content.Context;
import android.content.SharedPreferences;

public class VocabularyFetcher {

    public static void storeWord(Context context, String word, String translation) {
        SharedPreferences prefs = context.getSharedPreferences("vocabulary", Context.MODE_PRIVATE);
        prefs.edit().putString(word, translation).apply();
    }

    public static String fetchTranslation(Context context, String word) {
        SharedPreferences prefs = context.getSharedPreferences("vocabulary", Context.MODE_PRIVATE);
        return prefs.getString(word, "");
    }
}
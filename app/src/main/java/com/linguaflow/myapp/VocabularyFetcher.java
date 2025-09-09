package com.linguaflow.myapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VocabularyFetcher {

    public static void storeWord(Context context, String word, String translation) {
        SharedPreferences prefs = context.getSharedPreferences("vocabulary", Context.MODE_PRIVATE);
        prefs.edit().putString(word, translation).apply();
    }

    public static String fetchTranslation(Context context, String word) {
        SharedPreferences prefs = context.getSharedPreferences("vocabulary", Context.MODE_PRIVATE);
        return prefs.getString(word, "");
    }

    public static List<String> getAllEnglishWords(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("vocab_english", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = prefs.getAll();
        List<String> words = new ArrayList<>();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            words.add(entry.getKey());
        }

        return words;
    }
}
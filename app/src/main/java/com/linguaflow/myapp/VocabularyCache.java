package com.linguaflow.myapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class VocabularyCache {

    public static Map<String, String> load(Context context, String language) {
        SharedPreferences prefs = context.getSharedPreferences("vocab_" + language, Context.MODE_PRIVATE);
        Map<String, String> map = new HashMap<>();
        for (String key : prefs.getAll().keySet()) {
            Object value = prefs.getAll().get(key);
            if (value instanceof String) {
                map.put(key, (String) value);
            }
        }
        return map;
    }

    public static void save(Context context, String word, String translation, String example) {
        SharedPreferences prefs = context.getSharedPreferences("vocab_english", Context.MODE_PRIVATE);
        prefs.edit().putString(word, translation).apply();
        // Beispiel kann separat gespeichert werden, falls nötig
    }
}
package com.linguaflow.myapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class VocabularyCache {

    public static Map<String, String> load(Context context, String language) {
        SharedPreferences prefs = context.getSharedPreferences("vocab_" + language, Context.MODE_PRIVATE);
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, ?> entry : prefs.getAll().entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {
                map.put(entry.getKey(), (String) value);
            }
        }
        return map;
    }

    public static void save(Context context, String word, String translation, String example) {
        SharedPreferences prefs = context.getSharedPreferences("vocab_english", Context.MODE_PRIVATE);
        prefs.edit().putString(word, translation).apply();
        // Beispiel kann bei Bedarf separat gespeichert werden
    }

    public static void saveTranslation(Context context, String englishWord, String germanWord) {
        SharedPreferences prefs = context.getSharedPreferences("vocab_english", Context.MODE_PRIVATE);
        prefs.edit().putString(englishWord, germanWord).apply();
    }

    public static String getGerman(Context context, String englishWord) {
        Map<String, String> data = load(context, "english");
        if (data != null && data.containsKey(englishWord)) {
            return data.get(englishWord);
        } else {
            return "";
        }
    }
}
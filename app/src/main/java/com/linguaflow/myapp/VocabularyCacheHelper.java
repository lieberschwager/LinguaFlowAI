package com.linguaflow.myapp;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Hilfsklasse zum Auslesen aller gespeicherten Vokabeln aus dem Cache.
 * Funktioniert sprachunabhängig.
 */
public class VocabularyCacheHelper {

    public static List<String> getAllCachedWords(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("vocabulary_cache", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = prefs.getAll();
        Set<String> baseWords = new HashSet<>();

        for (String key : allEntries.keySet()) {
            if (key.contains("_")) {
                String base = key.substring(0, key.indexOf("_"));
                baseWords.add(base);
            }
        }

        return new ArrayList<>(baseWords);
    }
}
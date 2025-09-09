package com.linguaflow.myapp;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class DynamicLanguageManager {

    private static Map<String, Map<String, String>> translations = new HashMap<>();

    public static String getTranslation(Context context, String lang, String key) {
        Map<String, String> langMap;

        if (translations.containsKey(lang)) {
            langMap = translations.get(lang);
        } else {
            langMap = translations.get("en"); // Fallback
        }

        if (langMap == null) {
            return key; // Fallback auf Originaltext
        }

        return langMap.containsKey(key) ? langMap.get(key) : key;
    }

    public static void loadTranslations(String lang, Map<String, String> data) {
        translations.put(lang, data);
    }
}
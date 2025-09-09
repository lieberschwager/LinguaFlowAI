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
            return key;
        }

        return langMap.containsKey(key) ? langMap.get(key) : key;
    }

    // 🔧 Neue Methode: Alias für getTranslation mit Default-Sprache
    public static String getText(Context context, String key) {
        // Du kannst hier später dynamisch die Sprache aus den Einstellungen holen
        return getTranslation(context, "en", key);
    }

    public static void loadTranslations(String lang, Map<String, String> data) {
        translations.put(lang, data);
    }
}
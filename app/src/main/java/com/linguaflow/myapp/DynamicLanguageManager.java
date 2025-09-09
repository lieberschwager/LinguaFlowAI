package com.linguaflow.myapp;

import android.content.Context;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DynamicLanguageManager {

    private static final Map<String, Map<String, String>> translations = new HashMap<>();

    static {
        // Deutsch
        Map<String, String> de = new HashMap<>();
        de.put("favorites_title", "Deine Favoriten");
        de.put("favorites_empty", "Keine Favoriten gefunden.");
        de.put("stats_title", "Lernstatistik");
        de.put("stats_empty", "Kein Fortschritt erfasst.");
        translations.put("de", de);

        // Englisch
        Map<String, String> en = new HashMap<>();
        en.put("favorites_title", "Your Favorites");
        en.put("favorites_empty", "No favorites found.");
        en.put("stats_title", "Learning Stats");
        en.put("stats_empty", "No progress recorded yet.");
        translations.put("en", en);

        // Französisch (optional)
        Map<String, String> fr = new HashMap<>();
        fr.put("favorites_title", "Vos favoris");
        fr.put("favorites_empty", "Aucun favori trouvé.");
        fr.put("stats_title", "Statistiques d'apprentissage");
        fr.put("stats_empty", "Aucun progrès enregistré.");
        translations.put("fr", fr);
    }

    public static String getText(Context context, String key) {
        String lang = LanguageFetcher.getActiveLanguage(context);
        Map<String, String> langMap = translations.getOrDefault(lang, translations.get("en"));
        return langMap.getOrDefault(key, "[" + key + "]");
    }
}
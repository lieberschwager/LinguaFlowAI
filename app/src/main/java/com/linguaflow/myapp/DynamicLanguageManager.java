package com.linguaflow.myapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Führt die automatische Sprachintegration durch.
 * Erkennt neue Sprachen, aktualisiert die UI und speichert den Zustand.
 */
public class DynamicLanguageManager {

    private static final String PREF_KEY = "known_languages";

    public static void updateLanguages(Context context, Spinner spinner, ArrayList<String> fetchedLanguages) {
        if (fetchedLanguages == null || fetchedLanguages.isEmpty()) return;

        SharedPreferences prefs = context.getSharedPreferences("language_state", Context.MODE_PRIVATE);
        Set<String> known = prefs.getStringSet(PREF_KEY, new HashSet<>());

        Set<String> updated = new HashSet<>(fetchedLanguages);
        boolean hasNew = !updated.equals(known);

        if (hasNew) {
            prefs.edit().putStringSet(PREF_KEY, updated).apply();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_item, fetchedLanguages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public static boolean hasNewLanguages(Context context, ArrayList<String> fetchedLanguages) {
        SharedPreferences prefs = context.getSharedPreferences("language_state", Context.MODE_PRIVATE);
        Set<String> known = prefs.getStringSet(PREF_KEY, new HashSet<>());
        return !new HashSet<>(fetchedLanguages).equals(known);
    }
}
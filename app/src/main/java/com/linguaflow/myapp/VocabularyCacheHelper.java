package com.linguaflow.myapp;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VocabularyCacheHelper {

    public static List<String> getRandomTranslations(Context context, String excludeWord, int count) {
        SharedPreferences prefs = context.getSharedPreferences("vocabulary", Context.MODE_PRIVATE);
        List<String> allTranslations = new ArrayList<>();

        for (String key : prefs.getAll().keySet()) {
            if (!key.equals(excludeWord)) {
                String value = prefs.getString(key, null);
                if (value != null && !value.trim().isEmpty()) {
                    allTranslations.add(value.trim());
                }
            }
        }

        Collections.shuffle(allTranslations);
        if (allTranslations.size() > count) {
            return allTranslations.subList(0, count);
        } else {
            return allTranslations;
        }
    }
}
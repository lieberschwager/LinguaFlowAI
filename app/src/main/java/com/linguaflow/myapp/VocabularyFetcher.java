package com.linguaflow.myapp;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VocabularyFetcher {

    public static List<String> getAllEnglishWords(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("vocabulary", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = prefs.getAll();
        List<String> result = new ArrayList<>();

        for (String key : allEntries.keySet()) {
            if (key.contains("_")) {
                String english = key.split("_")[0];
                if (!result.contains(english)) {
                    result.add(english);
                }
            }
        }

        return result;
    }
}
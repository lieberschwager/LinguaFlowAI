package com.linguaflow.myapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VocabularyCacheHelper {

    public static List<String> getAllCachedWords(Context context) {
        Map<String, String> englishWords = VocabularyCache.load(context, "english");
        return new ArrayList<>(englishWords.keySet());
    }

    public static List<String> getRandomTranslations(Context context, String excludeWord, int count) {
        Map<String, String> all = VocabularyCache.load(context, "english");
        List<String> pool = new ArrayList<>();

        for (Map.Entry<String, String> entry : all.entrySet()) {
            if (!entry.getKey().equals(excludeWord)) {
                pool.add(entry.getValue());
            }
        }

        Collections.shuffle(pool);
        return pool.stream().limit(count).collect(Collectors.toList());
    }
}
package com.linguaflow.myapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VocabularyCacheHelper {

    public static List<String> getAllCachedWords(Context context) {
        Map<String, String> englishWords = VocabularyCache.load(context, "english");
        return new ArrayList<>(englishWords.keySet());
    }
}
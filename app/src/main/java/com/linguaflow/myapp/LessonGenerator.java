package com.linguaflow.myapp;

import android.content.Context;
import java.util.List;
import java.util.Map;

/**
 * Erstellt automatisch Lektionen aus englischen Vokabeln.
 * Nutzt VocabularyFetcher + VocabularyCache für Effizienz.
 */
public class LessonGenerator {

    public interface LessonCallback {
        void onLessonReady(Lesson lesson);
    }

    public static class Lesson {
        public final String english;
        public final String german;
        public final String example;

        public Lesson(String english, String german, String example) {
            this.english = english;
            this.german = german;
            this.example = example;
        }
    }

    public static void generate(Context context, String englishWord, LessonCallback callback) {
        if (VocabularyCache.contains(context, englishWord)) {
            Map<String, String> cached = VocabularyCache.load(context, englishWord);
            Lesson lesson = new Lesson(englishWord, cached.get("german"), cached.get("example"));
            callback.onLessonReady(lesson);
            return;
        }

        new VocabularyFetcher((translation, example) -> {
            Lesson lesson = new Lesson(englishWord, translation, example);
            VocabularyCache.save(context, englishWord, translation, example);
            callback.onLessonReady(lesson);
        }).execute(englishWord);
    }

    public static void generateBatch(Context context, List<String> englishWords, LessonCallback callback) {
        for (String word : englishWords) {
            generate(context, word, callback);
        }
    }
}
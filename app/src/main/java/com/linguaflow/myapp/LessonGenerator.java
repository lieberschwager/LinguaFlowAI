package com.linguaflow.myapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class LessonGenerator {

    public interface Callback {
        void onGenerated(Lesson lesson);
    }

    public static class Lesson {
        public String german;
        public String example;

        public Lesson(String german, String example) {
            this.german = german;
            this.example = example;
        }
    }

    public static void generate(Context context, String englishWord, Callback callback) {
        new Thread(() -> {
            String german = VocabularyCache.getGerman(context, englishWord);
            String example = ExampleGenerator.getExample(context, englishWord);

            if (german == null || german.trim().isEmpty()) {
                // Fallback: einfache Übersetzung simulieren
                german = simulateTranslation(englishWord);
                VocabularyCache.saveTranslation(context, englishWord, german);
            }

            if (example == null || example.trim().isEmpty()) {
                example = simulateExample(englishWord, german);
            }

            Lesson lesson = new Lesson(german, example);

            new Handler(Looper.getMainLooper()).post(() -> {
                callback.onGenerated(lesson);
            });
        }).start();
    }

    private static String simulateTranslation(String word) {
        return "DE_" + word; // Platzhalter-Übersetzung
    }

    private static String simulateExample(String english, String german) {
        return "I like " + english + " → Ich mag " + german;
    }
}
package com.linguaflow.myapp;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Generiert automatisch Quizfragen aus dem gespeicherten Wortschatz.
 */
public class QuizGenerator {

    public static class QuizQuestion {
        public final String questionText;
        public final List<String> options;
        public final int correctIndex;

        public QuizQuestion(String questionText, List<String> options, int correctIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctIndex = correctIndex;
        }
    }

    public static List<QuizQuestion> generateFromCache(Context context) {
        List<QuizQuestion> quizList = new ArrayList<>();
        List<String> allWords = VocabularyCacheHelper.getAllCachedWords(context);

        for (String english : allWords) {
            Map<String, String> data = VocabularyCache.load(context, english);
            String correctGerman = data.get("german");

            List<String> options = new ArrayList<>();
            options.add(correctGerman);

            // Falsche Antworten zufällig aus anderen Wörtern
            for (String other : allWords) {
                if (!other.equals(english) && options.size() < 4) {
                    String wrong = VocabularyCache.load(context, other).get("german");
                    if (!options.contains(wrong)) {
                        options.add(wrong);
                    }
                }
            }

            // Wenn weniger als 4 Optionen vorhanden, auffüllen
            while (options.size() < 4) {
                options.add("—");
            }

            Collections.shuffle(options);
            int correctIndex = options.indexOf(correctGerman);

            String questionText = "Was heißt \"" + english + "\" auf Deutsch?";
            quizList.add(new QuizQuestion(questionText, options, correctIndex));
        }

        return quizList;
    }
}
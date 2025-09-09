package com.linguaflow.myapp;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizManager {

    public static class QuizItem {
        public String question;
        public String correctAnswer;
        public List<String> options;

        public QuizItem(String question, String correctAnswer, List<String> options) {
            this.question = question;
            this.correctAnswer = correctAnswer;
            this.options = new ArrayList<>(options);
            if (!this.options.contains(correctAnswer)) {
                this.options.add(correctAnswer);
            }
            Collections.shuffle(this.options);
        }
    }

    public static List<QuizItem> generateQuiz(Context context, List<String> words) {
        List<QuizItem> quiz = new ArrayList<>();
        for (String word : words) {
            String translation = VocabularyCache.getGerman(context, word);
            if (translation != null && !translation.isEmpty()) {
                List<String> distractors = VocabularyCacheHelper.getRandomTranslations(context, word, 3);
                quiz.add(new QuizItem(word, translation, distractors));
            }
        }
        return quiz;
    }

    public static boolean isCorrect(QuizItem item, String selectedAnswer) {
        return item.correctAnswer.equals(selectedAnswer);
    }
}
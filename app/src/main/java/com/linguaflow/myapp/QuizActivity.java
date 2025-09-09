package com.linguaflow.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends Activity {

    private TextView questionText, feedbackText;
    private Button optionA, optionB, optionC, optionD;

    private String correctAnswer;
    private String englishWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        questionText = findViewById(R.id.questionText);
        feedbackText = findViewById(R.id.feedbackText);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);

        List<String> words = VocabularyFetcher.getAllEnglishWords(this);
        if (words.isEmpty()) {
            questionText.setText("No vocabulary available.");
            return;
        }

        Collections.shuffle(words);
        englishWord = words.get(0);
        correctAnswer = VocabularyCache.getGerman(this, englishWord);

        questionText.setText("What is the German word for '" + englishWord + "'?");

        List<String> options = VocabularyFetcher.getAllEnglishWords(this).subList(0, Math.min(4, words.size()));
        Collections.shuffle(options);

        // Ensure correct answer is among options
        if (!options.contains(englishWord)) {
            options.set(0, englishWord);
        }

        List<String> germanOptions = options.stream()
            .map(w -> VocabularyCache.getGerman(this, w))
            .toList();

        Button[] buttons = {optionA, optionB, optionC, optionD};
        for (int i = 0; i < germanOptions.size(); i++) {
            buttons[i].setText(germanOptions.get(i));
            String selected = germanOptions.get(i);
            buttons[i].setOnClickListener(v -> checkAnswer(selected));
        }
    }

    private void checkAnswer(String selected) {
        if (selected.equals(correctAnswer)) {
            feedbackText.setText("✅ Correct!");
            ProgressTracker.incrementProgress(this, englishWord);
        } else {
            feedbackText.setText("❌ Incorrect. Correct answer: " + correctAnswer);
        }
    }
}
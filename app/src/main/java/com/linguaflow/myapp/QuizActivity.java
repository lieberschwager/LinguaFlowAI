package com.linguaflow.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QuizActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        TextView questionView = findViewById(R.id.questionView);
        TextView answerView = findViewById(R.id.answerView);

        String english = "apple";
        String german = "Apfel";

        Map<String, String> data = VocabularyCache.load(this, "english");

        String correctAnswer;
        if (data != null && data.containsKey(english)) {
            correctAnswer = data.get(english);
        } else {
            correctAnswer = german;
        }

        // Ersetze Stream-Logik durch klassische Distinct-Logik
        List<String> rawOptions = Arrays.asList("Apfel", "Banane", "Orange", "Birne");
        Set<String> uniqueSet = new HashSet<>(rawOptions);
        List<String> options = new ArrayList<>(uniqueSet);

        questionView.setText("Was heißt '" + english + "' auf Deutsch?");
        answerView.setText("Antwort: " + correctAnswer);
    }
}
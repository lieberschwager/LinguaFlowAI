package com.linguaflow.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if (data.containsKey(english)) {
            correctAnswer = data.get(english);
        } else {
            correctAnswer = german;
        }

        List<String> options = Arrays.asList("Apfel", "Banane", "Orange", "Birne")
                .stream()
                .distinct()
                .collect(Collectors.toList());

        questionView.setText("Was heißt '" + english + "' auf Deutsch?");
        answerView.setText("Antwort: " + correctAnswer);
    }
}
package com.linguaflow.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class QuizResultActivity extends Activity {

    private TextView resultText, feedbackText;
    private Button retryButton, homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_result);

        resultText = findViewById(R.id.resultText);
        feedbackText = findViewById(R.id.feedbackText);
        retryButton = findViewById(R.id.retryButton);
        homeButton = findViewById(R.id.homeButton);

        int correct = getIntent().getIntExtra("correct", 0);
        int total = getIntent().getIntExtra("total", 0);

        resultText.setText("Du hast " + correct + " von " + total + " richtig beantwortet.");

        String feedback;
        float score = (float) correct / total;
        if (score == 1f) {
            feedback = "Perfekt! 🎉";
        } else if (score >= 0.75f) {
            feedback = "Sehr gut! Weiter so!";
        } else if (score >= 0.5f) {
            feedback = "Ganz okay – noch etwas üben.";
        } else {
            feedback = "Da geht noch was. Versuch's nochmal!";
        }
        feedbackText.setText(feedback);

        retryButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuizActivity.class);
            startActivity(intent);
            finish();
        });

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
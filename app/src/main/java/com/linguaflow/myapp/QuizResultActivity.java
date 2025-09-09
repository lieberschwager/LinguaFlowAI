package com.linguaflow.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class QuizResultActivity extends Activity {

    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_result);

        // Binde die einzige TextView im Layout
        resultText = findViewById(R.id.resultText);

        // Lade die übergebenen Extras
        int correctCount = getIntent().getIntExtra("correctCount", 0);
        int totalCount   = getIntent().getIntExtra("totalCount", 0);

        // Berechne Prozentsatz (falls totalCount > 0)
        String percentage;
        if (totalCount > 0) {
            int percent = Math.round(correctCount * 100f / totalCount);
            percentage = percent + "%";
        } else {
            percentage = "–";
        }

        // Baue das Ergebnis-Label
        String summary = "Richtig: " + correctCount
                       + " von " + totalCount
                       + " (" + percentage + ")";

        // Setze den Text
        resultText.setText(summary);
    }
}
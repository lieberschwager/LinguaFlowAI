package com.linguaflow.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class QuizResultActivity extends Activity {

    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result); // ✅ Layout-Verweis korrigiert

        // Binde die Ergebnis-TextView
        resultText = findViewById(R.id.resultText);

        // Lade die übergebenen Werte
        int correctCount = getIntent().getIntExtra("correctCount", 0);
        int totalCount   = getIntent().getIntExtra("totalCount", 0);

        // Berechne den Prozentsatz
        String percentage = (totalCount > 0)
            ? Math.round(correctCount * 100f / totalCount) + "%"
            : "–";

        // Ergebnis-Text zusammenbauen
        String summary = "Richtig: " + correctCount
                       + " von " + totalCount
                       + " (" + percentage + ")";

        // Text setzen
        resultText.setText(summary);
    }
}
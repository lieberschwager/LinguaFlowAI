package com.linguaflow.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LessonStatsActivity extends Activity {

    private TextView statsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_stats);

        // IDs, die in lesson_stats.xml wirklich existieren
        statsView = findViewById(R.id.statsView);

        // Falls du irgendwann doch einen Export-Button hinzufügen willst,
        // erstelle ihn im Layout mit android:id="@+id/exportButton"
        // und hol ihn dann hier per findViewById.
        //
        // exportButton = findViewById(R.id.exportButton);
        // exportButton.setOnClickListener(v -> exportStats());

        loadAndDisplayStats();
    }

    private void loadAndDisplayStats() {
        // Beispielsweise:
        int correct = getIntent().getIntExtra("correctCount", 0);
        int total = getIntent().getIntExtra("totalCount", 0);
        String text = "Richtige Antworten: " + correct + " von " + total;
        statsView.setText(text);
    }

    // private void exportStats() {
    //     // Export-Logik hier rein, sobald der Button im Layout steht
    // }
}
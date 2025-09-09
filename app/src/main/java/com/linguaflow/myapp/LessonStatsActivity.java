package com.linguaflow.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LessonStatsActivity extends Activity {

    // Bind an den TextView mit der ID statsText aus lesson_stats.xml
    private TextView statsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_stats);

        // Optional: Titel-TextView kannst du hier anpassen oder lokalizeren
        TextView titleText = findViewById(R.id.titleText);
        titleText.setText("Lernfortschritt");

        // Korrekte ID aus dem XML: statsText
        statsText = findViewById(R.id.statsText);

        loadAndDisplayStats();
    }

    private void loadAndDisplayStats() {
        // Werte aus Intent lesen
        int correct = getIntent().getIntExtra("correctCount", 0);
        int total   = getIntent().getIntExtra("totalCount", 0);

        // Text dynamisch zusammenbauen
        String summary = "Richtige Antworten: " + correct + " von " + total;
        statsText.setText(summary);
    }
}
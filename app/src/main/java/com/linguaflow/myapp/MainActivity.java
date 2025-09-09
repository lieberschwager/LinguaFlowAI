package com.linguaflow.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends Activity {

    private TextView welcomeText;
    private Button lessonButton, quizButton, favoritesButton, statsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Systemsprache setzen, falls keine gewählt
        String currentLang = LanguageFetcher.getActiveLanguage(this);
        if (currentLang == null || currentLang.trim().isEmpty()) {
            LanguageFetcher.setActiveLanguage(this, Locale.getDefault().getLanguage());
        }

        welcomeText = findViewById(R.id.welcomeText);
        lessonButton = findViewById(R.id.lessonButton);
        quizButton = findViewById(R.id.quizButton);
        favoritesButton = findViewById(R.id.favoritesButton);
        statsButton = findViewById(R.id.statsButton);

        welcomeText.setText("Welcome to LinguaFlow");

        lessonButton.setOnClickListener(v -> startActivity(new Intent(this, LessonActivity.class)));
        quizButton.setOnClickListener(v -> startActivity(new Intent(this, QuizActivity.class)));
        favoritesButton.setOnClickListener(v -> startActivity(new Intent(this, FavoritesActivity.class)));
        statsButton.setOnClickListener(v -> startActivity(new Intent(this, LessonStatsActivity.class)));

        // Optional: VocabularyFetcher.preload(this); // Nur beim ersten Start
    }
}
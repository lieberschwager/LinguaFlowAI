package com.linguaflow.myapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.Gravity;

public class MainActivity extends Activity {

    private TextView welcomeText;
    private Button lessonButton, quizButton, favoritesButton, statsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        welcomeText = findViewById(R.id.welcomeText);
        lessonButton = findViewById(R.id.lessonButton);
        quizButton = findViewById(R.id.quizButton);
        favoritesButton = findViewById(R.id.favoritesButton);
        statsButton = findViewById(R.id.statsButton);

        // Benutzername aus SharedPreferences holen
        SharedPreferences prefs = getSharedPreferences("LinguaPrefs", MODE_PRIVATE);
        String userName = prefs.getString("username", "");

        // Begrüßungstext setzen und zentrieren
        welcomeText.setText("Willkommen zurück, " + userName + "!");
        welcomeText.setGravity(Gravity.CENTER_HORIZONTAL);

        // Button-Aktionen
        lessonButton.setOnClickListener(v -> startActivity(new Intent(this, LessonActivity.class)));
        quizButton.setOnClickListener(v -> startActivity(new Intent(this, QuizActivity.class)));
        favoritesButton.setOnClickListener(v -> startActivity(new Intent(this, FavoritesActivity.class)));
        statsButton.setOnClickListener(v -> startActivity(new Intent(this, LessonStatsActivity.class)));
    }
}
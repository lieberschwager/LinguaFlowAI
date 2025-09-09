package com.linguaflow.myapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView greetingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity); // Stelle sicher, dass diese Layout-Datei existiert

        greetingText = findViewById(R.id.greetingText);

        SharedPreferences prefs = getSharedPreferences("LinguaPrefs", MODE_PRIVATE);
        String userName = prefs.getString("username", "Lernender");
        String langCode = prefs.getString("language", "en");

        String languageName = convertToLanguageName(langCode);
        String greeting = "Willkommen zurück, " + userName + "! Deine Lernsprache ist " + languageName + ".";

        greetingText.setText(greeting);
    }

    private String convertToLanguageName(String code) {
        switch (code) {
            case "de": return "Deutsch";
            case "en": return "Englisch";
            case "es": return "Spanisch";
            case "fr": return "Französisch";
            default: return "Unbekannt";
        }
    }
}
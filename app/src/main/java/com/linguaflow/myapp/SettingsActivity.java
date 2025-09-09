package com.linguaflow.myapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SettingsActivity extends Activity {

    private EditText nameInput;
    private Spinner languageSelector;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingsactivity); // ✅ angepasst an deine Layout-Datei

        nameInput = findViewById(R.id.nameInput);
        languageSelector = findViewById(R.id.languageSelector);
        continueButton = findViewById(R.id.continueButton);

        String[] languages = {"Deutsch", "Englisch", "Spanisch", "Französisch"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this, android.R.layout.simple_spinner_dropdown_item, languages);
        languageSelector.setAdapter(adapter);

        continueButton.setOnClickListener(v -> {
            String userName = nameInput.getText().toString().trim();
            String selectedLang = languageSelector.getSelectedItem().toString();
            String langCode = convertToLangCode(selectedLang);

            SharedPreferences prefs = getSharedPreferences("LinguaPrefs", MODE_PRIVATE);
            prefs.edit()
                .putString("username", userName)
                .putString("language", langCode)
                .apply();

            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }

    private String convertToLangCode(String language) {
        switch (language) {
            case "Deutsch": return "de";
            case "Englisch": return "en";
            case "Spanisch": return "es";
            case "Französisch": return "fr";
            default: return "en";
        }
    }
}
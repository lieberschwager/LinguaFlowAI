package com.linguaflow.myapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class ProfileActivity extends Activity {

    private EditText nameInput;
    private TextView progressView;
    private Button saveButton;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        initViews();
        loadProfileData();
        updateProgressView();
        setupSaveButton();
    }

    private void initViews() {
        nameInput = findViewById(R.id.nameInput);
        progressView = findViewById(R.id.progressView);
        saveButton = findViewById(R.id.saveButton);
        prefs = getSharedPreferences("profile", MODE_PRIVATE);
    }

    private void loadProfileData() {
        String savedName = prefs.getString("username", "");
        nameInput.setText(savedName);
    }

    private void updateProgressView() {
        SharedPreferences progressPrefs = getSharedPreferences("progress", MODE_PRIVATE);
        int totalLearned = progressPrefs.getAll().size();
        progressView.setText("Gelernt: " + totalLearned + " Wörter");
    }

    private void setupSaveButton() {
        saveButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(this, "Bitte Namen eingeben.", Toast.LENGTH_SHORT).show();
                return;
            }
            prefs.edit().putString("username", name).apply();
            Toast.makeText(this, "Gespeichert als " + name, Toast.LENGTH_SHORT).show();
        });
    }
}
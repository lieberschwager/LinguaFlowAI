package com.linguaflow.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends Activity {

    private TextView welcomeText;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        initViews();
        applyAnimations();
        setupButtonClick();
    }

    private void initViews() {
        welcomeText = findViewById(R.id.welcomeText);
        startButton = findViewById(R.id.startButton);
    }

    private void applyAnimations() {
        try {
            Animation textFade = AnimationUtils.loadAnimation(this, R.anim.text_fade_in);
            welcomeText.startAnimation(textFade);
        } catch (Exception e) {
            Toast.makeText(this, "Text-Animation fehlt oder fehlerhaft", Toast.LENGTH_SHORT).show();
        }

        try {
            Animation bounce = AnimationUtils.loadAnimation(this, R.anim.start_button_bounce);
            startButton.startAnimation(bounce);
        } catch (Exception e) {
            Toast.makeText(this, "Button-Animation fehlt oder fehlerhaft", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupButtonClick() {
        startButton.setEnabled(true);
        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}
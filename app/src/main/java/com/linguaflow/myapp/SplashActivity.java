package com.linguaflow.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2000; // Dauer in Millisekunden

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Verweist auf deine Layout-Datei

        // 🌈 Rainbow Overlay einbinden (View existiert in activity_splash.xml)
        View rainbowOverlay = findViewById(R.id.rainbowOverlay);
        if (rainbowOverlay != null) {
            rainbowOverlay.setAlpha(0.3f); // Optional: Transparenz setzen
        }

        // ⏳ Nach SPLASH_DURATION zur MainActivity wechseln
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // SplashActivity beenden
        }, SPLASH_DURATION);
    }
}
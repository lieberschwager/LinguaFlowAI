package com.linguaflow.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 20000; // 20 Sekunden

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 🌈 Rainbow Overlay einbinden (bereits im Layout vorhanden)
        View rainbowOverlay = findViewById(R.id.rainbowOverlay);
        if (rainbowOverlay != null) {
            rainbowOverlay.setAlpha(0.3f);
        }

        // ⏳ Nach 20 Sekunden zu StartActivity wechseln
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);
    }
}
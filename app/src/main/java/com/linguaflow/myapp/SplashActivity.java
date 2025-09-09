package com.linguaflow.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends Activity {

    private static final int TEXT_DELAY_MS = 1200;
    private static final int TRANSITION_DELAY_MS = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ImageView logo = findViewById(R.id.logo);
        TextView splashText = findViewById(R.id.splashText);

        // Starte Logo-Animation
        Animation logoAnim = AnimationUtils.loadAnimation(this, R.anim.logo_enter);
        logo.startAnimation(logoAnim);

        // Starte Text-Animation nach kurzer Verzögerung
        new Handler().postDelayed(() -> {
            Animation textAnim = AnimationUtils.loadAnimation(this, R.anim.text_enter);
            splashText.startAnimation(textAnim);
        }, TEXT_DELAY_MS);

        // Übergang zur Startseite nach definierter Zeit
        new Handler().postDelayed(() -> {
            Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
            splashText.startAnimation(fadeOut);
            logo.startAnimation(fadeOut);

            Intent intent = new Intent(SplashActivity.this, StartActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, TRANSITION_DELAY_MS);
    }
}
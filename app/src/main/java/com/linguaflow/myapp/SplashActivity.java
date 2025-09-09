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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ImageView logo = findViewById(R.id.logo);
        TextView splashText = findViewById(R.id.splashText);

        // Lade und starte Logo-Animation
        Animation logoAnim = AnimationUtils.loadAnimation(this, R.anim.logo_enter);
        logo.startAnimation(logoAnim);

        // Lade und starte Text-Animation mit leichter Verzögerung
        new Handler().postDelayed(() -> {
            Animation textAnim = AnimationUtils.loadAnimation(this, R.anim.text_enter);
            splashText.startAnimation(textAnim);
        }, 1200); // Startet nach Logo-Animation

        // Nach 6 Sekunden: sanfter Übergang zur Startseite
        new Handler().postDelayed(() -> {
            splashText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out));
            startActivity(new Intent(SplashActivity.this, StartActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, 6000);
    }
}
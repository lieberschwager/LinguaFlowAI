package com.linguaflow.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    private static final int TRANSITION_DELAY_MS = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ImageView logo = findViewById(R.id.logo);

        // Starte Logo-Animation
        Animation logoAnim = AnimationUtils.loadAnimation(this, R.anim.logo_enter);
        logo.startAnimation(logoAnim);

        // Nach TRANSITION_DELAY_MS zur StartActivity wechseln
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, StartActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.text_fade_in, R.anim.start_fade_out);
            finish();
        }, TRANSITION_DELAY_MS);
    }
}
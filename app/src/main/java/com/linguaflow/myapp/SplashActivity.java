package com.linguaflow.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    private static final int SPLASH_DURATION = 2000; // 2 Sekunden

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ImageView logo = findViewById(R.id.splashLogo);
        startLogoAnimation(logo);
        navigateToStartAfterDelay();
    }

    private void startLogoAnimation(ImageView logo) {
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.text_fade_in);
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        AnimationSet combo = new AnimationSet(true);
        combo.addAnimation(fadeIn);
        combo.addAnimation(slideUp);
        combo.setDuration(SPLASH_DURATION);

        logo.startAnimation(combo);
    }

    private void navigateToStartAfterDelay() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);
    }
}
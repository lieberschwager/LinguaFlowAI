package com.linguaflow.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash); // ✅ Layoutname korrekt

        ImageView logo = findViewById(R.id.logo); // ❗ Stelle sicher, dass das im Layout existiert
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo.startAnimation(fadeIn);
    }
}
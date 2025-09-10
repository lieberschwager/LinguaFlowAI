package com.linguaflowai.app; // ← Passe das an deinen tatsächlichen Package-Namen an

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View rainbowOverlay = findViewById(R.id.rainbowOverlay);

        ObjectAnimator animator = ObjectAnimator.ofFloat(rainbowOverlay, "translationX", -200f, 200f);
        animator.setDuration(4000);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.start();
    }
}
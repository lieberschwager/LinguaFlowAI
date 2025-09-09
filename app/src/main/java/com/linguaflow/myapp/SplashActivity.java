package com.linguaflow.myapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.animation.ObjectAnimator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.view.animation.ScaleAnimation;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ImageView logo = findViewById(R.id.logo);
        TextView splashText = findViewById(R.id.splashText);

        // Bewegung: Logo schwebt von oben
        TranslateAnimation moveDown = new TranslateAnimation(0, 0, -500, 0);
        moveDown.setDuration(3000);
        moveDown.setFillAfter(true);

        // Skalierung: sanftes Einblenden
        ScaleAnimation scaleUp = new ScaleAnimation(
            0.8f, 1.0f, 0.8f, 1.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f);
        scaleUp.setDuration(3000);
        scaleUp.setFillAfter(true);

        // Kombinierte Bewegung
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(moveDown);
        animationSet.addAnimation(scaleUp);
        logo.startAnimation(animationSet);

        // Regenbogeneffekt auf Logo
        ObjectAnimator colorAnim = ObjectAnimator.ofArgb(
            logo, "colorFilter",
            Color.RED, Color.MAGENTA, Color.BLUE, Color.CYAN, Color.GREEN, Color.YELLOW);
        colorAnim.setDuration(6000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

        // Text bewegt sich von unten
        TranslateAnimation textMove = new TranslateAnimation(0, 0, 500, 0);
        textMove.setDuration(3000);
        textMove.setFillAfter(true);
        splashText.startAnimation(textMove);

        // Weiterleitung nach 15 Sekunden
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        }, 15000);
    }
}
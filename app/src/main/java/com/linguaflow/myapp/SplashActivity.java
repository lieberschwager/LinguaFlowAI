package com.linguaflow.myapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ImageView logo = findViewById(R.id.logo);
        TextView titleText = findViewById(R.id.titleText);

        // 🔍 Zoom-In Bewegung
        ObjectAnimator logoZoomX = ObjectAnimator.ofFloat(logo, "scaleX", 0.5f, 1f);
        ObjectAnimator logoZoomY = ObjectAnimator.ofFloat(logo, "scaleY", 0.5f, 1f);
        ObjectAnimator textZoomX = ObjectAnimator.ofFloat(titleText, "scaleX", 0.5f, 1f);
        ObjectAnimator textZoomY = ObjectAnimator.ofFloat(titleText, "scaleY", 0.5f, 1f);

        // 🔄 Bewegung von oben/unten
        ObjectAnimator logoMove = ObjectAnimator.ofFloat(logo, "translationY", -300f, 0f);
        ObjectAnimator textMove = ObjectAnimator.ofFloat(titleText, "translationY", 300f, 0f);

        // ⏱ Dauer
        logoZoomX.setDuration(1500);
        logoZoomY.setDuration(1500);
        textZoomX.setDuration(1500);
        textZoomY.setDuration(1500);
        logoMove.setDuration(1500);
        textMove.setDuration(1500);

        // 🎬 Kombinieren
        AnimatorSet zoomSet = new AnimatorSet();
        zoomSet.playTogether(logoZoomX, logoZoomY, textZoomX, textZoomY, logoMove, textMove);
        zoomSet.start();

        // 🌈 Regenbogen-Farbwechsel
        int[] rainbowColors = {
            getResources().getColor(R.color.rainbowRed),
            getResources().getColor(R.color.rainbowOrange),
            getResources().getColor(R.color.rainbowYellow),
            getResources().getColor(R.color.rainbowGreen),
            getResources().getColor(R.color.rainbowBlue),
            getResources().getColor(R.color.rainbowIndigo),
            getResources().getColor(R.color.rainbowViolet)
        };

        ValueAnimator colorAnimator = ValueAnimator.ofInt(0, rainbowColors.length - 1);
        colorAnimator.setDuration(3000);
        colorAnimator.addUpdateListener(anim -> {
            int index = (int) anim.getAnimatedValue();
            titleText.setTextColor(rainbowColors[index]);
            logo.setColorFilter(rainbowColors[index]);
        });
        colorAnimator.start();

        // ⏳ Weiterleitung zur StartActivity nach 15 Sekunden
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        }, 15000); // Splashscreen bleibt 15 Sekunden sichtbar
    }
}
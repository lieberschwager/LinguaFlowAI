package com.linguaflow.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {

    private static final int TRANSITION_DELAY_MS = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Dynamisch erzeugtes Layout mit splash_background als Hintergrund
        RelativeLayout layout = new RelativeLayout(this);
        layout.setBackgroundResource(R.drawable.splash_background);

        setContentView(layout);

        // Nach kurzer Verzögerung zur StartActivity wechseln
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, StartActivity.class));
            finish();
        }, TRANSITION_DELAY_MS);
    }
}
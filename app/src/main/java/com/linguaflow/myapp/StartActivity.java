package com.linguaflow.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends Activity {

    private TextView welcomeText;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        initViews();
        animateWelcomeText();
        animateStartButton();
    }

    private void initViews() {
        welcomeText = findViewById(R.id.welcomeText);
        startButton = findViewById(R.id.startButton);
    }

    private void animateWelcomeText() {
        Animation textFade = AnimationUtils.loadAnimation(this, R.anim.text_fade_in);
        welcomeText.startAnimation(textFade);
    }

    private void animateStartButton() {
        Animation bounce = AnimationUtils.loadAnimation(this, R.anim.start_button_bounce);
        startButton.startAnimation(bounce);
    }
}
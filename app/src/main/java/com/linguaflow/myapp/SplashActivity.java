package com.linguaflow.myapp;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import com.linguaflow.myapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // MotionLayout starten
        MotionLayout motionLayout = findViewById(R.id.motionLayout);
        motionLayout.transitionToEnd();

        // Globus laden
        WebView globeView = findViewById(R.id.globeView);
        globeView.getSettings().setJavaScriptEnabled(true);
        globeView.setWebViewClient(new WebViewClient());
        globeView.loadUrl("file:///android_asset/globe.html");
    }
}
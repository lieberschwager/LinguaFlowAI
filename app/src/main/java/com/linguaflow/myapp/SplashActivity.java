package com.linguaflow.myapp;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private WebView globeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Vollbild & transparente Statusleiste
        Window window = getWindow();
        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );

        // WebView initialisieren
        globeView = new WebView(this);
        globeView.setBackgroundColor(Color.parseColor("#98FF98")); // Mintgrün

        setContentView(globeView);

        // WebView konfigurieren
        WebSettings webSettings = globeView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // globe.html laden
        globeView.loadUrl("file:///android_asset/globe.html");
    }
}
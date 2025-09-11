package com.linguaflow.myapp;

import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private WebView globeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        globeView = new WebView(this);

        // Hintergrund transparent setzen
        globeView.setBackgroundColor(Color.TRANSPARENT);

        // LayerType NICHT setzen – hat Darstellung zerstört
        // globeView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null); ← entfernt!

        setContentView(globeView);

        WebSettings webSettings = globeView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        globeView.loadUrl("file:///android_asset/globe.html");
    }
}
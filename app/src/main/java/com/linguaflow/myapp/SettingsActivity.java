package com.linguaflow.myapp;

import android.app.Activity;
import android.os.Bundle;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Übergangsanimation beim Öffnen der SettingsActivity
        overridePendingTransition(R.anim.text_fade_in, R.anim.start_fade_out);
    }

    @Override
    public void finish() {
        super.finish();
        // Übergangsanimation beim Verlassen der SettingsActivity
        overridePendingTransition(R.anim.start_fade_out, R.anim.text_fade_in);
    }
}
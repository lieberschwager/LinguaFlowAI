package com.linguaflow.myapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;

public class LessonActivity extends Activity {

    private TextToSpeech tts;
    private String englishWord;
    private String germanWord = "";
    private Button speakButton, favoriteButton;
    private TextView progressText, englishText, germanText, exampleText;
    private SharedPreferences prefs;
    private boolean isFavorite = false;
    private int progressCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson);

        englishWord = getIntent().getStringExtra("english");

        initViews();
        setupTextToSpeech();
        setupSpeakButton();
        setupFavoriteLogic();
        updateProgress();
        generateLesson();
    }

    private void initViews() {
        englishText = findViewById(R.id.englishText);
        germanText = findViewById(R.id.germanText);
        exampleText = findViewById(R.id.exampleText);
        progressText = findViewById(R.id.progressText);
        speakButton = findViewById(R.id.speakButton);
        favoriteButton = findViewById(R.id.favoriteButton);

        englishText.setText("Englisch: " + englishWord);
        germanText.setText("Deutsch: ...");
        exampleText.setText("Beispiel wird geladen...");
    }

    private void generateLesson() {
        LessonGenerator.generate(this, englishWord, lesson -> {
            germanWord = lesson.german;
            germanText.setText("Deutsch: " + germanWord);
            exampleText.setText(lesson.example);
        });
    }

    private void setupTextToSpeech() {
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.ENGLISH);
            }
        });
    }

    private void setupSpeakButton() {
        speakButton.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak(englishWord, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }

    private void setupFavoriteLogic() {
        prefs = getSharedPreferences("favorites", MODE_PRIVATE);
        isFavorite = prefs.getBoolean(englishWord, false);
        updateFavoriteButton();

        favoriteButton.setOnClickListener(v -> {
            isFavorite = !isFavorite;
            prefs.edit().putBoolean(englishWord, isFavorite).apply();
            updateFavoriteButton();
        });
    }

    private void updateFavoriteButton() {
        favoriteButton.setText(isFavorite ? "Favorit entfernen" : "Zu Favoriten");
    }

    private void updateProgress() {
        SharedPreferences progressPrefs = getSharedPreferences("progress", MODE_PRIVATE);
        progressCount = progressPrefs.getInt(englishWord, 0) + 1;
        progressPrefs.edit().putInt(englishWord, progressCount).apply();
        progressText.setText("Gelernt: " + progressCount + "×");
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
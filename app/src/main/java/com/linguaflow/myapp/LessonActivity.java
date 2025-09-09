package com.linguaflow.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

public class LessonActivity extends Activity {

    private TextView wordText, translationText, exampleText, loadingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson);

        wordText = findViewById(R.id.wordText);
        translationText = findViewById(R.id.translationText);
        exampleText = findViewById(R.id.exampleText);
        loadingText = findViewById(R.id.loadingText);

        loadingText.setText("Loading...");

        List<String> words = VocabularyFetcher.getAllEnglishWords(this);
        if (words.isEmpty()) {
            wordText.setText("-");
            translationText.setText("-");
            exampleText.setText("-");
            loadingText.setText("No vocabulary available.");
            return;
        }

        Collections.shuffle(words);
        String english = words.get(0);
        String translation = VocabularyCache.getGerman(this, english);

        wordText.setText(english);
        translationText.setText(translation != null ? translation : "-");
        exampleText.setText("I like to eat an " + english + ".");
        loadingText.setText(""); // fertig geladen
    }
}
package com.linguaflow.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity {

    private final String[][] lessons = {
        {"Hello", "Hallo"},
        {"Thank you", "Danke"},
        {"Goodbye", "Auf Wiedersehen"}
    };

    private Spinner languageSpinner;
    private ProgressBar loadingBar;
    private TextView statusText;
    private Button retryButton, favoritesButton, progressButton, quizButton, profileButton;
    private ListView lessonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initViews();
        setupLessonList();
        setupButtons();
        fetchLanguages();
    }

    private void initViews() {
        languageSpinner = findViewById(R.id.languageSpinner);
        loadingBar = findViewById(R.id.loadingBar);
        statusText = findViewById(R.id.statusText);
        retryButton = findViewById(R.id.retryButton);
        favoritesButton = findViewById(R.id.favoritesButton);
        progressButton = findViewById(R.id.progressButton);
        quizButton = findViewById(R.id.quizButton);
        profileButton = findViewById(R.id.profileButton);
        lessonList = findViewById(R.id.lessonList);
    }

    private void setupLessonList() {
        ArrayList<String> displayList = new ArrayList<>();
        for (String[] pair : lessons) {
            displayList.add(pair[0] + " → " + pair[1]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, displayList);
        lessonList.setAdapter(adapter);

        lessonList.setOnItemClickListener((parent, view, position, id) -> {
            String[] pair = lessons[position];
            Intent intent = new Intent(this, LessonActivity.class);
            intent.putExtra("english", pair[0]);
            intent.putExtra("german", pair[1]);
            startActivity(intent);
        });
    }

    private void setupButtons() {
        retryButton.setOnClickListener(v -> fetchLanguages());

        favoritesButton.setOnClickListener(v ->
                startActivity(new Intent(this, FavoritesActivity.class)));

        progressButton.setOnClickListener(v ->
                startActivity(new Intent(this, ProgressActivity.class)));

        quizButton.setOnClickListener(v ->
                startActivity(new Intent(this, QuizActivity.class)));

        profileButton.setOnClickListener(v ->
                startActivity(new Intent(this, ProfileActivity.class)));
    }

    private void fetchLanguages() {
        loadingBar.setVisibility(View.VISIBLE);
        statusText.setVisibility(View.GONE);
        retryButton.setVisibility(View.GONE);

        new LanguageFetcher() {
            @Override
            protected void onPostExecute(ArrayList<String> result) {
                loadingBar.setVisibility(View.GONE);

                if (result == null || result.isEmpty()) {
                    statusText.setText("Fehler beim Laden der Sprachen.");
                    statusText.setVisibility(View.VISIBLE);
                    retryButton.setVisibility(View.VISIBLE);
                    return;
                }

                String systemLang = Locale.getDefault().getLanguage();
                if (result.contains(systemLang)) {
                    result.remove(systemLang);
                    result.add(0, systemLang);
                }

                if (DynamicLanguageManager.hasNewLanguages(MainActivity.this, result)) {
                    Toast.makeText(MainActivity.this, "Neue Sprachen verfügbar!", Toast.LENGTH_SHORT).show();
                }

                DynamicLanguageManager.updateLanguages(MainActivity.this, languageSpinner, result);
            }
        }.execute();
    }
}
package com.linguaflow.myapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LessonReviewActivity extends Activity {

    private ListView wordListView;
    private Button filterFavoritesButton;
    private boolean showOnlyFavorites = false;
    private List<String> allWords;
    private ArrayAdapter<String> adapter;
    private SharedPreferences favoritesPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_review);

        wordListView = findViewById(R.id.wordListView);
        filterFavoritesButton = findViewById(R.id.filterFavoritesButton);
        favoritesPrefs = getSharedPreferences("favorites", MODE_PRIVATE);

        allWords = VocabularyCacheHelper.getAllCachedWords(this);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        wordListView.setAdapter(adapter);

        filterFavoritesButton.setOnClickListener(v -> {
            showOnlyFavorites = !showOnlyFavorites;
            updateList();
            filterFavoritesButton.setText(showOnlyFavorites ? "Alle anzeigen" : "Nur Favoriten");
        });

        updateList();
    }

    private void updateList() {
        List<String> displayList = new ArrayList<>();

        for (String word : allWords) {
            boolean isFavorite = favoritesPrefs.getBoolean(word, false);
            if (!showOnlyFavorites || isFavorite) {
                Map<String, String> data = VocabularyCache.load(this, word);
                String german = data.get("german");
                String example = data.get("example");
                int count = ProgressTracker.getCount(this, word);
                boolean mastered = ProgressTracker.isMastered(this, word);

                String status = mastered ? "✅ gelernt" : "🔄 wiederholen";
                displayList.add(word + " → " + german + "\n" + example + "\nFortschritt: " + count + "× • " + status);
            }
        }

        adapter.clear();
        adapter.addAll(displayList);
        adapter.notifyDataSetChanged();
    }
}
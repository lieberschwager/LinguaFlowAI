package com.linguaflow.myapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Map;

public class FavoritesActivity extends Activity {

    private ListView favoritesList;
    private SharedPreferences prefs;
    private ArrayList<String> favoriteWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);

        initViews();
        loadFavorites();
        setupList();
    }

    private void initViews() {
        favoritesList = findViewById(R.id.favoritesList);
        prefs = getSharedPreferences("favorites", MODE_PRIVATE);
        favoriteWords = new ArrayList<>();
    }

    private void loadFavorites() {
        Map<String, ?> allFavorites = prefs.getAll();
        for (Map.Entry<String, ?> entry : allFavorites.entrySet()) {
            if (Boolean.TRUE.equals(entry.getValue())) {
                favoriteWords.add(entry.getKey());
            }
        }

        if (favoriteWords.isEmpty()) {
            Toast.makeText(this, "Keine Favoriten vorhanden.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, favoriteWords);
        favoritesList.setAdapter(adapter);

        favoritesList.setOnItemClickListener((parent, view, position, id) -> {
            String word = favoriteWords.get(position);
            prefs.edit().remove(word).apply();
            favoriteWords.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, word + " entfernt.", Toast.LENGTH_SHORT).show();
        });
    }
}
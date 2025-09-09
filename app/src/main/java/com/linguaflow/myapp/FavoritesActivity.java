package com.linguaflow.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Set;

public class FavoritesActivity extends Activity {

    private TextView titleText, favoritesText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);

        titleText = findViewById(R.id.titleText);
        favoritesText = findViewById(R.id.favoritesText);

        titleText.setText(DynamicLanguageManager.getText(this, "favorites_title"));

        Set<String> favorites = FavoritesManager.getAllFavorites(this);
        if (favorites.isEmpty()) {
            favoritesText.setText(DynamicLanguageManager.getText(this, "favorites_empty"));
        } else {
            StringBuilder sb = new StringBuilder();
            for (String word : favorites) {
                String translation = VocabularyCache.getGerman(this, word);
                sb.append("• ").append(word).append(" → ").append(translation != null ? translation : "-").append("\n");
            }
            favoritesText.setText(sb.toString().trim());
        }
    }
}
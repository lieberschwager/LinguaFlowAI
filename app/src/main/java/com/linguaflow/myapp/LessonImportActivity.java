package com.linguaflow.myapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.linguaflow.myapp.dialog.ConflictResolutionDialogFragment;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.*;

public class LessonImportActivity extends FragmentActivity {

    private static final int PICK_JSON_FILE = 1005;
    private Button selectButton, confirmButton;
    private CheckBox overwriteCheckbox;
    private ListView previewList;
    private ArrayAdapter<String> adapter;
    private JSONArray previewData;
    private Set<String> existingWords;
    private Queue<JSONObject> conflictQueue = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_import);

        selectButton = findViewById(R.id.selectButton);
        confirmButton = findViewById(R.id.confirmButton);
        overwriteCheckbox = findViewById(R.id.overwriteCheckbox);
        previewList = findViewById(R.id.previewList);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        previewList.setAdapter(adapter);

        selectButton.setOnClickListener(v -> openFilePicker());
        confirmButton.setOnClickListener(v -> {
            if (previewData != null) {
                importWithDialog();
            } else {
                Toast.makeText(this, "Keine Daten geladen", Toast.LENGTH_SHORT).show();
            }
        });

        existingWords = new HashSet<>(VocabularyCacheHelper.getAllCachedWords(this));
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("application/json");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, PICK_JSON_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_JSON_FILE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            loadPreview(uri);
        }
    }

    private void loadPreview(Uri uri) {
        try {
            InputStream is = getContentResolver().openInputStream(uri);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            String jsonString = new String(buffer);
            JSONObject root = new JSONObject(jsonString);
            previewData = root.getJSONArray("vocabulary");

            ArrayList<String> previewListItems = new ArrayList<>();
            for (int i = 0; i < previewData.length(); i++) {
                JSONObject obj = previewData.getJSONObject(i);
                String word = obj.getString("word");
                String german = obj.getString("german");
                String example = obj.getString("example");
                int repetitions = obj.getInt("repetitions");

                boolean exists = existingWords.contains(word);
                String status = exists ? "⚠️ Bereits vorhanden" : "🆕 Neu";

                previewListItems.add(status + " " + word + " → " + german + "\n" + example + "\nWiederholt: " + repetitions + "×");
            }

            adapter.clear();
            adapter.addAll(previewListItems);
            adapter.notifyDataSetChanged();

            Toast.makeText(this, "Vorschau geladen", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Fehler beim Laden: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void importWithDialog() {
        try {
            for (int i = 0; i < previewData.length(); i++) {
                JSONObject obj = previewData.getJSONObject(i);
                String word = obj.getString("word");

                if (existingWords.contains(word)) {
                    conflictQueue.add(obj);
                } else {
                    saveEntry(obj);
                }
            }

            if (!conflictQueue.isEmpty()) {
                showNextConflict();
            } else {
                Toast.makeText(this, "Import abgeschlossen: keine Konflikte", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Fehler beim Import: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void showNextConflict() {
        if (conflictQueue.isEmpty()) {
            Toast.makeText(this, "Import abgeschlossen: alle Konflikte gelöst", Toast.LENGTH_LONG).show();
            return;
        }

        JSONObject obj = conflictQueue.poll();
        String word = obj.optString("word");
        String newGerman = obj.optString("german");
        String newExample = obj.optString("example");

        Map<String, String> existing = VocabularyCache.load(this, word);
        String existingGerman = existing.get("german");
        String existingExample = existing.get("example");

        ConflictResolutionDialogFragment dialog = new ConflictResolutionDialogFragment(
                word,
                existingGerman, existingExample,
                newGerman, newExample,
                new ConflictResolutionDialogFragment.ConflictResolutionListener() {
                    @Override
                    public void onKeepExisting() {
                        showNextConflict();
                    }

                    @Override
                    public void onOverwrite() {
                        saveEntry(obj);
                        showNextConflict();
                    }
                });

        dialog.show(getSupportFragmentManager(), "ConflictDialog");
    }

    private void saveEntry(JSONObject obj) {
        try {
            String word = obj.getString("word");
            String german = obj.getString("german");
            String example = obj.getString("example");
            int repetitions = obj.getInt("repetitions");
            long lastReviewed = obj.getLong("lastReviewed");

            VocabularyCache.save(this, word, german, example);
            ProgressTracker.set(this, word, repetitions, lastReviewed);

        } catch (Exception e) {
            Toast.makeText(this, "Fehler beim Speichern: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
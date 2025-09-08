package com.linguaflow.myapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Map;

public class ProgressActivity extends Activity {

    private ListView progressList;
    private SharedPreferences prefs;
    private ArrayList<String> progressItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);

        initViews();
        loadProgressData();
        setupList();
    }

    private void initViews() {
        progressList = findViewById(R.id.progressList);
        prefs = getSharedPreferences("progress", MODE_PRIVATE);
        progressItems = new ArrayList<>();
    }

    private void loadProgressData() {
        Map<String, ?> allProgress = prefs.getAll();
        for (Map.Entry<String, ?> entry : allProgress.entrySet()) {
            if (entry.getValue() instanceof Integer) {
                int count = (Integer) entry.getValue();
                progressItems.add(entry.getKey() + " → gelernt: " + count + "×");
            }
        }

        if (progressItems.isEmpty()) {
            Toast.makeText(this, "Noch keine Lernfortschritte vorhanden.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, progressItems);
        progressList.setAdapter(adapter);

        progressList.setOnItemClickListener((parent, view, position, id) -> {
            String item = progressItems.get(position);
            String word = item.contains(" →") ? item.split(" →")[0] : item;
            prefs.edit().remove(word).apply();
            progressItems.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, word + " zurückgesetzt.", Toast.LENGTH_SHORT).show();
        });
    }
}
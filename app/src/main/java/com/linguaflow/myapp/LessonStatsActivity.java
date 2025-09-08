package com.linguaflow.myapp;

import android.app.DatePickerDialog;
import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileOutputStream;
import java.util.*;

public class LessonStatsActivity extends Activity {

    private Button exportButton, dateButton;
    private Spinner filterSpinner;
    private Calendar selectedDate = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_stats);

        exportButton = findViewById(R.id.exportButton);
        dateButton = findViewById(R.id.dateButton);
        filterSpinner = findViewById(R.id.filterSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Alle", "Nur Neue", "Nur Wiederholte"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(adapter);

        dateButton.setOnClickListener(v -> openDatePicker());
        exportButton.setOnClickListener(v -> exportFilteredProgress());
    }

    private void openDatePicker() {
        DatePickerDialog dialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    selectedDate.set(year, month, dayOfMonth, 0, 0, 0);
                    Toast.makeText(this, "Datum gewählt: " + dayOfMonth + "." + (month + 1) + "." + year, Toast.LENGTH_SHORT).show();
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void exportFilteredProgress() {
        try {
            List<String> allWords = VocabularyCacheHelper.getAllCachedWords(this);
            JSONArray jsonArray = new JSONArray();

            String selectedFilter = filterSpinner.getSelectedItem().toString();
            long cutoff = selectedDate.getTimeInMillis();

            for (String word : allWords) {
                int count = ProgressTracker.getCount(this, word);
                long last = ProgressTracker.getLastTimestamp(this, word);
                String status = ProgressBadge.getStatus(this, word).name();

                boolean include = last >= cutoff &&
                        (selectedFilter.equals("Alle")
                        || (selectedFilter.equals("Nur Neue") && count == 0)
                        || (selectedFilter.equals("Nur Wiederholte") && count > 0));

                if (!include) continue;

                JSONObject obj = new JSONObject();
                obj.put("word", word);

                Map<String, String> data = VocabularyCache.load(this, word);
                obj.put("german", data.get("german"));
                obj.put("example", data.get("example"));
                obj.put("repetitions", count);
                obj.put("lastReviewed", last);
                obj.put("status", status);

                jsonArray.put(obj);
            }

            JSONObject root = new JSONObject();
            root.put("vocabulary", jsonArray);

            FileOutputStream fos = openFileOutput("progress_filtered_by_date.json", MODE_PRIVATE);
            fos.write(root.toString(2).getBytes());
            fos.close();

            Toast.makeText(this, "Export abgeschlossen: " + jsonArray.length() + " Einträge", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this, "Fehler beim Export: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
package com.linguaflow.myapp;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Holt Vokabeln und Übersetzungen aus Wiktionary.
 * Beispiel: "Hello" → "Hallo" + Beispielsatz
 */
public class VocabularyFetcher extends AsyncTask<String, Void, String> {

    private static final String TAG = "VocabularyFetcher";

    public interface Callback {
        void onResult(String translation, String example);
    }

    private final Callback callback;

    public VocabularyFetcher(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params) {
        String word = params[0];
        String apiUrl = "https://en.wiktionary.org/w/api.php?action=query&titles=" + word +
                "&prop=revisions&rvprop=content&format=json";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            return response.toString();

        } catch (Exception e) {
            Log.e(TAG, "Fehler beim Abrufen von Wiktionary", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(String rawJson) {
        if (rawJson == null || rawJson.isEmpty()) {
            callback.onResult("Keine Übersetzung gefunden", "");
            return;
        }

        // Übersetzung extrahieren (vereinfachter Regex für Deutsch)
        Pattern translationPattern = Pattern.compile("\\|de\\s*=\\s*(\\w+)");
        Matcher matcher = translationPattern.matcher(rawJson);
        String translation = matcher.find() ? matcher.group(1) : "Keine Übersetzung";

        // Beispielsatz (optional, hier nur Platzhalter)
        String example = "Beispiel: I say \"" + translation + "\" when I greet someone.";

        callback.onResult(translation, example);
    }
}
package com.linguaflow.myapp;

/**
 * Globale API-Konfiguration für LinguaFlowAI.
 * Enthält Authentifizierungsinformationen und Endpunkte.
 */
public class ApiConfig {

    // Authentifizierungstoken für API-Zugriffe
    public static final String TOKEN = "f1b39c5cfe46c5a8eb5aa091582f9281ce735d81";

    // Basis-URL für alle API-Endpunkte
    public static final String BASE_URL = "https://api.aaapis.com/v1/";

    // Beispiel-Endpunkt für Länderabfrage
    public static final String ENDPOINT_COUNTRIES = BASE_URL + "countries";

    // Weitere Endpunkte können hier ergänzt werden
}
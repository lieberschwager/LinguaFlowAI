# LinguaFlowAI 🇩🇪🇬🇧

**LinguaFlowAI** ist eine Android-App zum spielerischen Lernen von Sprachen — mit Fokus auf Deutsch und Englisch. Sie kombiniert Quiz, Fortschrittsverfolgung, Text-to-Speech und Favoritenverwaltung in einem modernen UI.

---

## 🚀 Features

- **Splashscreen mit Animationen**  
- **Startscreen mit Bounce-Button und Begrüßungstext**  
- **Lektionsliste mit Klicknavigation**  
- **Favoritenverwaltung** (hinzufügen/entfernen)  
- **Lernfortschritt pro Wort**  
- **Quiz mit Multiple-Choice und Auswertung**  
- **Profilansicht mit Namensspeicherung und Gesamtfortschritt**  
- **Text-to-Speech für englische Wörter**  
- **Sprachauswahl via API (aaapis.com)**

---

## 🧩 Struktur

```plaintext
├── res/
│   ├── layout/           → XML-Dateien für UI
│   ├── anim/             → Animationen (slide_up, bounce, etc.)
│   ├── drawable/         → Button-Styles
│   └── values/           → Farben, Styles, Strings
├── java/com/linguaflow/myapp/
│   ├── SplashActivity.java
│   ├── StartActivity.java
│   ├── MainActivity.java
│   ├── LessonActivity.java
│   ├── FavoritesActivity.java
│   ├── ProgressActivity.java
│   ├── QuizActivity.java
│   ├── ProfileActivity.java
│   ├── LanguageFetcher.java
│   └── ApiConfig.java
└── AndroidManifest.xml
#!/data/data/com.termux/files/usr/bin/bash

echo "🔁 Syncing globe files from main → assets..."

cp ./app/src/main/globe.html ./app/src/main/assets/globe.html
cp ./app/src/main/globe.js ./app/src/main/assets/globe.js

echo "✅ Assets aktualisiert."

echo "🚀 Starte lokalen Webserver..."
cd ./app/src/main
python3 -m http.server
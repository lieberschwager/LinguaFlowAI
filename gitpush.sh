#!/bin/bash

echo "📝 Commit-Nachricht eingeben:"
read -r msg

git add .
git commit -m "$msg"
git push

echo "✅ Alles gepusht."
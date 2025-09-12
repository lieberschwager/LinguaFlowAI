# 🧭 Navigation
alias F1='cd ~/LinguaFlowAI && ls -la'              # Projektverzeichnis anzeigen

# 🚀 Git
alias F2='bash ~/LinguaFlowAI/gitpush.sh'           # Git push

# 🔄 Sync
alias F3='bash ~/LinguaFlowAI/sync.sh'              # Projekt synchronisieren

# 🌐 Webserver
alias F4='cd ~/LinguaFlowAI/app/src/main && nohup python -m http.server 8000 &'  # Webflow starten
alias F5='pkill -f "python -m http.server"'         # Webflow stoppen

# 🔁 Bash & Aliase
alias F6='source ~/.bashrc'                         # Bash neu laden
alias F7='source ~/LinguaFlowAI/aliases.sh'         # Aliase neu laden

# 💾 Backup
alias F8='cp ~/.bashrc ~/LinguaFlowAI/bashrc_backup.txt'  # Bashrc sichern

# 📝 DEVLOG
alias F9='nano ~/LinguaFlowAI/DEVLOG.md'            # DEVLOG öffnen

# 🧪 Test oder frei belegbar
alias F10='echo "F10 ist noch frei – nutze mich!"'
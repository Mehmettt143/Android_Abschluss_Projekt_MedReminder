## MedReminder <img width="64" height="64" alt="logo1" src="https://github.com/user-attachments/assets/ab300660-8b3e-4367-be08-9e82c520d26d" />

- 📋 Einfache **Medikamentenverwaltung mit Erinnerungsfunktion**.
- 📱 MedReminder ist eine Android-App zur **Medikamentensuche**.
- 🔍 Nutzer können **Medikamente suchen, zu Favoriten hinzufügen und Einnahme-Erinnerungen erstellen**.
- 👥 Die App richtet sich an alle, die ihre Medikamenteneinnahme organisieren möchten und dabei auf offizielle **FDA-Informationen** zugreifen wollen.

## 🎨 Design
- folgt noch... 

### 📱 Screens:
- [ ] 🔍 DrugsSearchScreen
- [ ] ⭐ FavoriteDrugsScreen (Favoriten)
- [ ] ⚙️ SettingNotificationScreen

### ✨ Features
- [ ] 🔍 Medikamentensuche über FDA-Datenbank
- [ ] ⭐ Medikamente zu Favoriten hinzufügen
- [ ] ⏰ Einnahme-Erinnerungen erstellen
- [ ] 📱 Push-Benachrichtigungen

## 🛠️ Technischer Aufbau

### 🏗️ MVVM Architektur 

<img width="280" height="580" alt="ArchitekturDiagramm" src="https://github.com/user-attachments/assets/28944942-ce10-486b-bf76-c2c6bfb0f870" />

### 📁 Ordnerstruktur
```
data/
├── local/        # 💾 Room Database
├── remote/       # 🌐 OpenFDA API
└── repository/   # 📦 Repository Implementation
di/               # 🔧 Dependency Injection
navigation/       # 🧭 Navigation
ui/
├── components/   # 🧩 Fertige Components zur Benutzung in den Screens
├── screens/      # 📱 Compose Screens
├── theme/        # 🎨 Color, Theme und Type
├── viewmodel/    # 🎯 ViewModels
└── AppStart
utils/            # 🔨 Helperclasses
MainActivity.kt
```

## 💾 Datenspeicherung

### 🗄️ Room Database:
- Speichert Favoriten und Erinnerungen lokal für Offline-Nutzung.

## 🌐 API Calls

**Website für die OpenFDA**: https://open.fda.gov/apis/

- **🔗 OpenFDA Drug Labeling API**:
- **📍 URL**: `https://api.fda.gov/drug/label.json`
- **💰 Kostenlos**, ohne API-Key
- **🔍 Suche nach**: Markenname, Wirkstoff, Hersteller
   
## 📚 3rd-Party Frameworks

- **🎨 Jetpack Compose** (UI)
- **💾 Room** (lokale Datenbank)
- **🌐 Retrofit** (API Calls)
- **🔧 Dependency Injection**
- **🔔 Notification** (Benachrichtigung)

## 🚀 Ausblick

- [ ] 🌍 Die Medikamenteninformationen sind in englischer Sprache - könnten eventuell auf Deutsch übersetzt werden mit Hilfe einer API
- [ ] 📄 Die Favoritenliste oder auch Medikationen könnten als PDF an WhatsApp-Kontakte geschickt werden

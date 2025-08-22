## MedReminder <img width="64" height="64" alt="logo1" src="https://github.com/user-attachments/assets/ab300660-8b3e-4367-be08-9e82c520d26d" />

Eine Android-App zur Medikamentenverwaltung mit intelligenter Erinnerungsfunktion.

📱 Was ist MedReminder?
MedReminder hilft Nutzern dabei, ihre Medikamenteneinnahme zu organisieren und nie wieder eine Dosis zu vergessen. Die App bietet Zugriff auf offizielle FDA-Datenbanken und ermöglicht eine sichere, lokale Verwaltung persönlicher Medikationspläne.

## Zielgruppe

Patienten mit regelmäßiger Medikation
Senioren, die Unterstützung bei der Medikamentenorganisation benötigen
Alle, die ihre Gesundheit bezüglich Medikation verwalten möchten

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
- **🔄 Moshi** (JSON Converter - API Daten in Kotlin Objects)
- **🌐 Retrofit** (API Calls)
- **🔧 Dependency Injection**
- **🔔 Notification** (Benachrichtigung)



## 🚀 Ausblick

- [ ] 🌍 Die Medikamenteninformationen sind in englischer Sprache - könnten eventuell auf Deutsch übersetzt werden mit Hilfe einer API
- [ ] 📄 Die Favoritenliste oder auch Medikationen könnten als PDF an WhatsApp-Kontakte geschickt werden

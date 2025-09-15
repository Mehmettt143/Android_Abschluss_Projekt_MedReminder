## MedReminder <img width="64" height="64" alt="logo1" src="https://github.com/user-attachments/assets/ab300660-8b3e-4367-be08-9e82c520d26d" />

Eine Android-App zur Medikamentenverwaltung mit intelligenter Erinnerungsfunktion.

Was ist MedReminder?
MedReminder hilft Nutzern dabei, ihre Medikamenteneinnahme zu organisieren und nie wieder eine Dosis zu vergessen. Die App bietet Zugriff auf offizielle FDA-Datenbanken und ermöglicht eine sichere, lokale Verwaltung persönlicher Medikationspläne.

## Zielgruppe

Patienten mit regelmäßiger Medikation
Senioren, die Unterstützung bei der Medikamentenorganisation benötigen
Alle, die ihre Gesundheit bezüglich Medikation verwalten möchten

## Design
<img width="165.25" height="312.25" alt="SplashScreen" src="https://github.com/user-attachments/assets/ebf8d7e6-364e-49bc-8640-6d536f6f6c01" />
<img width="165.25" height="312.25" alt="AuthScreen" src="https://github.com/user-attachments/assets/afaae146-8424-4e70-a12d-ee2346e053fe" /><img width="165.25" height="312.25" alt="HomeScreen" src="https://github.com/user-attachments/assets/6e74b2b5-81e7-4d33-b14e-5dcc1e8457a9" />
<img width="165.25" height="312.25" alt="FavoriteScreen" src="https://github.com/user-attachments/assets/5185cfe3-f65c-4849-bbe6-2063036e1c4d" /><img width="165.25" height="312.25" alt="AddScreen" src="https://github.com/user-attachments/assets/129f29cd-631c-41f7-a78f-cb6659005ffd"/><img width="165,25" height="312.25" alt="SettingsScreen" src="https://github.com/user-attachments/assets/ddf4574a-9d09-4184-91a0-61eaa313c21c" />








###  Screens:
- [ ]  AuthScreen (Login&Registration)
- [ ]  HomeScreen (Search)
- [ ]  FavoriteScreen (Favoriten)
- [ ]  SettingsScreen (Export und Notification)
- [ ]  AddScreen

###  Features
- [ ]  Medikamentensuche über FDA-Datenbank
- [ ]  Medikamente zu Favoriten hinzufügen
- [ ]  Einnahme-Erinnerungen erstellen


##  Technischer Aufbau

###    MVVM Architektur 

<img width="250" height="550" alt="ArchitekturDiagramm" src="https://github.com/user-attachments/assets/28944942-ce10-486b-bf76-c2c6bfb0f870" />

### 📁 Ordnerstruktur
```
data/
   local/        #  Room Database
   remote/       #  OpenFDA API
   repository/   #  Repository Implementation
di/              #  Dependency Injection
navigation/      #  Navigation
ui/
   components/   #  Fertige Components zur Benutzung in den Screens
   screens/      #  Compose Screens
   theme/        #  Color, Theme und Type
   viewmodel/    #  ViewModels
   AppStart
utils/           #  Helperclasses
MainActivity.kt
```

## 💾 Datenspeicherung

### Firestore:
- Speichert Favoriten und Erinnerungen lokal für Offline-Nutzung.

## 🌐 API Calls

**Website für die OpenFDA**: https://open.fda.gov/apis/

-  OpenFDA Drug Labeling API:
-  URL: `https://api.fda.gov/drug/label.json`
-  Kostenlos, ohne API-Key
-  Suche nach: Markenname (brandname & genericname)
   
## 📚 3rd-Party Frameworks

-  Jetpack Compose (UI)
-  Room (lokale Datenbank)
-  Moshi (JSON Converter - API Daten in Kotlin Objects)
-  Retrofit (API Calls)
-  Dependency Injection
-  Notification** (Benachrichtigung)



## 🚀 Ausblick

- [ ]  Die Medikamenteninformationen sind in englischer Sprache - könnten eventuell auf Deutsch übersetzt werden mit Hilfe einer API
- [ ]  Die Favoritenliste oder auch Medikationen könnten als PDF an WhatsApp-Kontakte geschickt werden

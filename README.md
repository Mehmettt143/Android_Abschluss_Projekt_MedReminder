## MedReminder
Eine Android-App zur Medikamentenverwaltung mit intelligenter Erinnerungsfunktion.

Was ist MedReminder?
MedReminder hilft Nutzern dabei, ihre Medikamenteneinnahme zu organisieren und nie wieder eine Dosis zu vergessen. Die App bietet Zugriff auf offizielle FDA-Datenbanken und ermöglicht eine sichere Verwaltung persönlicher Medikamenteneinnahme.

## Zielgruppe

Patienten mit regelmäßiger Medikation
Senioren, die Unterstützung bei der Medikamentenorganisation benötigen.
Alle, die ihre Gesundheit bezüglich Medikation verwalten möchten.

## Design

<img width="165.25" height="312.25" alt="Login" src="https://github.com/user-attachments/assets/8f571fe8-de2e-4d8a-abe0-d1aa0eff221a" />

<img width="165.25" height="312.25" alt="Homescreen" src="https://github.com/user-attachments/assets/8c733c68-57a3-4b1a-b11e-c88e0c26cc69" />

<img width="165.25" height="312.25" alt="Medications" src="https://github.com/user-attachments/assets/a156d662-8886-408b-aa5b-dec2827ba9b9" />

<img width="165.25" height="312.25" alt="Add" src="https://github.com/user-attachments/assets/adc18a94-ca8f-4d74-9d94-a89affd354d6" />

<img width="165.25" height="312.25" alt="Settings" src="https://github.com/user-attachments/assets/8176eda4-6799-4fdb-bd88-99b6664d657b" />










###  Screens:
-   AuthScreen (Login&Registration)
-   HomeScreen (Search & Einnahme-Erinnerungen + Logout-Icon)
-   FavoriteScreen (Favoriten)
-   AddScreen (Einnahme-Erinnerungen erstellen)
-   SettingsScreen (Export und Notification + Logout)


###  Features
-   Medikamentensuche über FDA-Datenbank
-   Medikamente zu Favoriten hinzufügen + Details zeigen (Nebenwirkungen,Dosierung..)
-   Einnahme-Erinnerungen erstellen & löschen



## Technische Umsetzung
- **MVVM-Architektur:** Gewährleistet eine saubere und wartbare Code-Struktur.
- **Jetpack Compose:** Ermöglicht eine moderne und reaktive Benutzeroberfläche.

### 📁 Ordnerstruktur
```
data/
   remote/        firebase firestore database
   remote/        OpenFDA API
   repository/    Repository Implementation
di/               Dependency Injection
navigation/       Navigation
ui/
   components/    Fertige Components zur Benutzung in den Screens
   screens/       Compose Screens
   theme/         Color, Theme und Type
   viewmodel/     ViewModels
   AppStart
utils/            Helperclasses
MainActivity.kt
```

## 💾 Datenspeicherung

### Firestore:
- Speichert Favoriten und Erinnerungen remote in Firestore-database.(Zurzeit Testversion Firebase abgelaufen)

## 🌐 API Calls

**Website für die OpenFDA**: https://open.fda.gov/apis/

-  OpenFDA Drug Labeling API:
-  URL: `https://api.fda.gov/drug/label.json`
-  Kostenlos, ohne API-Key
-  Suche nach: Markenname (brandname & genericname)
   
## 📚 3rd-Party Frameworks

-  Jetpack Compose (UI)
-  firebase (remote Speicherung)
-  Moshi (JSON Converter - API Daten in Kotlin Objects)
-  Retrofit (API Calls)
-  Dependency Injection




## 🚀 Ausblick

-   Die Medikamenteninformationen sind in englischer Sprache - könnten eventuell auf           Deutsch übersetzt werden mit Hilfe einer API
-   Die Favoritenliste oder auch Medikamentenliste könnte als PDF exportiert werden
-   Notification (Benachrichtigung für Medikamenten-Einnahme)


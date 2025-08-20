#  <ins>MedReminder<img width="68" height="69" alt="drugs (1)" src="https://github.com/user-attachments/assets/5e476506-649e-4e15-9164-0da04159b2b6" />


- Einfache **Medikamentenverwaltung mit Erinnerungsfunktion**.
- MedReminder ist eine Android-App zur **Medikamentensuche**.
- Nutzer können **Medikamente suchen, zu Favoriten hinzufügen und Einnahme-Erinnerungen erstellen**.
- Die App richtet sich an alle, die ihre Medikamenteneinnahme organisieren möchten und dabei auf offizielle **FDA-Informationen** zugreifen wollen.

## Design
- folgt noch...


### Screens:
- [ ] DrugsSearchScreen
- [ ] FavoriteDrugsScreen (Favoriten)
- [ ] SettingNotificationScreen


### Features
- [ ] Medikamentensuche über FDA-Datenbank
- [ ] Medikamente zu Favoriten hinzufügen
- [ ] Einnahme-Erinnerungen erstellen
- [ ] Push-Benachrichtigungen

## Technischer Aufbau
### Ordnerstruktur
- data/
   - local/        # Room Database
   - remote/       # OpenFDA API
   - repository/   # Repository Implementation
- di/ #dependency Injection
- navigation/ # 
- ui/
	- component # fertige Components zur Benutzung in den Screens
	- screens/      # Compose Screens
	- theme/  # color,Theme und Type
	- viewmodel/    # ViewModels
 	- AppStart
- MainActivity.kt

  ### MVVM Architektur mit Repository Pattern
  
  
  <img width="500" height="1000" alt="ArchitekturDiagramm" src="https://github.com/user-attachments/assets/28944942-ce10-486b-bf76-c2c6bfb0f870" />



## Datenspeicherung
### Room Database:
- Speichert Favoriten und Erinnerungen lokal für offline-Nutzung.

## API Calls
- OpenFDA Drug Labeling API:
- URL: https://api.fda.gov/drug/label.json
- Kostenlos, ohne API-Key
- Suche nach: Markenname, Wirkstoff, Hersteller
   
## 3rd-Party Frameworks
- Jetpack Compose (UI)
- Room (lokale Datenbank)
- Retrofit (API Calls)
- Dependency Injection
- WorkManager(Erinnerungen)

## Ausblick
- [ ] die Medikamenteninformationen sind auf Englischer Sprache könnte eventuell auf Deutsch übersetzt werden mit Hilfe einer API 

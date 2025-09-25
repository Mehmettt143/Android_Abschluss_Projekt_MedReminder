package com.example.medreminder.utils


/*
*
*zur Validierung und Formatierung von Zeiteingaben  (z.B. Stunden, Minuten)
*
* */
object TimeHelper {

    /**
    * validiert und formatiert eine Zeiteingabe.
    *@param newValue Die eingegebene Zeichenkette (z.B. "5","12")
    * @param range Der zulässige Wertebereich (z.B. 0..23 für Stunden, 0..59 für Minuten)
    *@return Die validierte und formatierte Zeichenkette (z.B. 0.5 12) oder leer bei ungültiger Eingabe
    * */

    internal fun validateTimeInput(newValue: String, range: IntRange): String {
        return if (newValue.length <= 2 && newValue.all { it.isDigit() } &&
            (newValue.isEmpty() || newValue.toIntOrNull()?.let { it in range } == true)
        ) {
            newValue // Otomatik doldurma yok
        } else {
            ""
        }
    }
}

/*
* Es wird newValue.lentgh <=2 kontrollliert ob der Zahl maximal 2 stellen hat && und neuer wert aus
* Zahlen besteht und && nicht leer ist oder|| das dieser Zahl von String nach int umgwewandelt werden kann
* wenn nicht null wenn ja dan wird es durch .let überprüft ob der Wert innerhalb der Range liegt.
* der neue Wert wird dann returned
* wenn nicht wird es leer returned
* */
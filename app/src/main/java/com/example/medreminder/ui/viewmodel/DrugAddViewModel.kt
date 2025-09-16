package com.example.medreminder.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medreminder.data.local.DrugReminder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DrugAddViewModel : ViewModel() {


    // Speicherzustand
    private val _isSaving = MutableStateFlow(false)
    val isSaving = _isSaving.asStateFlow()

    // Speichern erfolgreich oder nicht
    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess = _saveSuccess.asStateFlow()

    // DrugReminder funktion zum Speichern
    fun saveDrug(
        medicationName: String,
        dosage: String,
        selectedTime: String,
        timeHour: String,
        timeMinute: String
    ) {
        viewModelScope.launch {
            try {
                // Speichervorgang
                _isSaving.value = true

                // Drug erzeugen
                val drug = DrugReminder(
                    id = System.currentTimeMillis().toString(),
                    brandName = medicationName,
                    dosage = dosage,
                    selectedTime = selectedTime,
                    timeHour = timeHour,
                    timeMinute = timeMinute,
                )


                delay(2000)

                // firestore speicherung
                //

                println("Save: $drug")

                // erfolgreich gespeichert
                _saveSuccess.value = true
                _isSaving.value = false

            } catch (e: Exception) {
                // Fehler
                _isSaving.value = false
                println("Error: ${e.message}")
            }
        }
    }


    fun resetSaveSuccess() {
        _saveSuccess.value = false
    }
}

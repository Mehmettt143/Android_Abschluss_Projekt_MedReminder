package com.example.medreminder.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medreminder.data.local.DrugReminder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DrugAddViewModel : ViewModel() {


    // Kaydetme durumu için state
    private val _isSaving = MutableStateFlow(false)
    val isSaving = _isSaving.asStateFlow()

    // Kaydetme başarı durumu
    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess = _saveSuccess.asStateFlow()

    // Drug kaydetme fonksiyonu
    fun saveDrug(medicationName: String, dosage: String, selectedTime: String, timeHour: String, timeMinute: String)
    { viewModelScope.launch {
            try {
                // Kaydetme işlemi başladı
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

                // firestore
                // firestore.collection("drugs").add(drug)

                println("Kaydedildi: $drug")

                // erfolgreich gespeichert
                _saveSuccess.value = true
                _isSaving.value = false

            } catch (e: Exception) {
                // Fehler
                _isSaving.value = false
                println("Fehler: ${e.message}")
            }
        }
    }


    fun resetSaveSuccess() {
        _saveSuccess.value = false
    }
}

package com.example.medreminder.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medreminder.data.repository.IDrugRepository
import com.example.medreminder.data.remote.firebase.model.DrugReminder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DrugAddViewModel(
    private val drugRepository: IDrugRepository
) : ViewModel() {

    // UI Zustände
    //Ladezustand während des Speicherns
    private val _isSaving = MutableStateFlow(false)
    val isSaving = _isSaving.asStateFlow()

    //Erfolgszustand nach dem Speichern
    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess = _saveSuccess.asStateFlow()

    private val _drugName = MutableStateFlow<String>("")
    val drugName = _drugName.asStateFlow()

    private val _drugDosis = MutableStateFlow<String>("")
    val drugDosis = _drugDosis.asStateFlow()

    private val _selectedTime = MutableStateFlow<String>("")
    val selectedTime = _selectedTime.asStateFlow()

    private val _timeHour = MutableStateFlow<String>("00")
    val timeHour = _timeHour.asStateFlow()

    private val _timeMinute = MutableStateFlow<String>("00")
    val timeMinute = _timeMinute.asStateFlow()

    fun updateDrugName(name: String) {
        _drugName.value = name
    }

    fun updateDrugDosis(dosis: String) {
        _drugDosis.value = dosis
    }

    fun updateSelectedTime(time: String) {
        _selectedTime.value = time
    }

    fun updateTimeHour(hour: String) {
        _timeHour.value = hour
    }

    fun updateTimeMinute(minute: String) {
        _timeMinute.value = minute
    }

    // Medikament speichern
    fun saveDrug(
        medicationName: String,
        dosage: String,
        selectedTime: String,
        timeHour: String,
        timeMinute: String
    ) {
        viewModelScope.launch {
            try {
                _isSaving.value = true

                // Drugreminder Objekt erstellen
                val drugReminder = DrugReminder(
                    id = System.currentTimeMillis().toString(),
                    brandName = medicationName,
                    dosage = dosage,
                    selectedTime = selectedTime,
                    timeHour = timeHour,
                    timeMinute = timeMinute,
                )
                println("DrugAddViewModel: Saving drug reminder: $drugReminder")
                // in Firestore speichern
                val result = drugRepository.addDrug(drugReminder)

                result.onSuccess {
                    println("DrugAddViewModel: Successfully saved drug reminder")
                    _saveSuccess.value = true
                }.onFailure { e ->
                    println("DrugAddViewModel: Failed to save drug reminder: ${e.message}")
                }
            } catch (e: Exception) {
                println("DrugAddViewModel: Exception in saveDrug: ${e.message}")

            } finally {
                //Ladezustand beenden
                _isSaving.value = false
            }
        }
    }

    //Erfolgszustand zurücksetzen
    fun resetSaveSuccess() {
        _saveSuccess.value = false

    }

    fun clearForm() {
        _drugName.value = ""
        _drugDosis.value = ""
        _selectedTime.value = ""
        _timeHour.value = "00"
        _timeMinute.value = "00"
    }

}
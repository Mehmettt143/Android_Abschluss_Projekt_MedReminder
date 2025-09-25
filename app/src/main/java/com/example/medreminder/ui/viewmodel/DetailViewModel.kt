package com.example.medreminder.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.medreminder.data.repository.IDrugRepository
import com.example.medreminder.data.remote.drugapi.model.DisplayDrug
import com.example.medreminder.navigation.DetailScreenRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val drugRepository: IDrugRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    //Medikament-ID aus Navigationsargumenten holen und
    val args = savedStateHandle.toRoute<DetailScreenRoute>()
    val drugId: String = args.drugId

    //Medikamentendetails
    private val _drug = MutableStateFlow<DisplayDrug?>(null)
    val drug: StateFlow<DisplayDrug?> = _drug.asStateFlow()

    //Ladezustand für die UI
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    //Fehlermeldungen
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        // Details laden
        try {
            loadDrugDetails(drugId)
        } catch (e: Exception) {
            _errorMessage.value = "Failed to load drug details $e"
        }
    }

    //Medikaments-Details laden
    private fun loadDrugDetails(drugId: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true


                val favoritesResult = drugRepository.getAllFavorites()

                favoritesResult.onSuccess { firebaseDrugs ->
                    val favoriteDrug = firebaseDrugs.find { it.id == drugId }
                    if (favoriteDrug != null) {
                        _drug.value = DisplayDrug(
                            id = favoriteDrug.id,
                            brandName = favoriteDrug.brandName,
                            genericName = favoriteDrug.genericName,
                            manufacturerName = favoriteDrug.manufacturerName,
                            indicationsAndUsage = favoriteDrug.indicationsAndUsage,
                            dosageAndAdministration = favoriteDrug.dosageAndAdministration,
                            warnings = favoriteDrug.warnings,
                            adverseReactions = favoriteDrug.adverseReactions
                        )
                    } else {
                        // wenn nicht in Favoriten von Api laden
                        loadDrugFromApi(drugId)
                    }
                }.onFailure {
                    // Favorilerden yüklenemezse direkt API'den dene
                    loadDrugFromApi(drugId)
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unexpected error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    //Api laden
    private suspend fun loadDrugFromApi(drugId: String) {
        try {
            val searchQuery = "id:\"$drugId\""
            val result = drugRepository.getDrugs(searchQuery, limit = 1)

            result.onSuccess { drugs ->
                _drug.value = drugs.firstOrNull()
                if (drugs.isEmpty()) {
                    _errorMessage.value = "Drug not found"
                }
            }.onFailure { e ->
                _errorMessage.value = e.message ?: "Failed to load drug"
            }
        } catch (e: Exception) {
            _errorMessage.value = e.message ?: "Failed to load drug from API"
        }
    }
}
package com.example.medreminder.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medreminder.data.repository.IDrugRepository
import com.example.medreminder.data.remote.drugapi.model.DisplayDrug
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val drugRepository: IDrugRepository
) : ViewModel() {

    //Liste der favorisierten Medikamente
    private val _favoriteDrugs = MutableStateFlow<List<DisplayDrug>>(emptyList())
    val favoriteDrugs: StateFlow<List<DisplayDrug>> = _favoriteDrugs.asStateFlow()

    //Ladezustand für die UI
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        //Favoriten beim Start laden
        loadFavorites()
    }

    //Favoriten aus dem Repository laden
    private fun loadFavorites() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = drugRepository.getAllFavorites()
                result.onSuccess { firebaseDrugs ->
                    val displayDrugs = firebaseDrugs.map { drug ->
                        DisplayDrug(
                            id = drug.id,
                            brandName = drug.brandName,
                            genericName = drug.genericName,
                            manufacturerName = drug.manufacturerName,
                            indicationsAndUsage = drug.indicationsAndUsage,
                            dosageAndAdministration = drug.dosageAndAdministration,
                            warnings = drug.warnings,
                            adverseReactions = drug.adverseReactions
                        )
                    }
                    _favoriteDrugs.value = displayDrugs
                }
            } catch (e: Exception) {
                println("FavoriteViewModel: loadFavorites failed: ${e.message}")
            }
            _isLoading.value = false
        }
    }

    //Medikament aus den Favoriten entfernen
    fun removeFavorite(drugId: String) {
        viewModelScope.launch {
            drugRepository.removeFavorite(drugId)
            _favoriteDrugs.value = _favoriteDrugs.value.filter { it.id != drugId }
        }
    }
}
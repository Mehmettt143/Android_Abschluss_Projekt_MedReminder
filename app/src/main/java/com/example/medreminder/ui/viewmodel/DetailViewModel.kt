package com.example.medreminder.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.medreminder.data.remote.drugapi.DrugApi
import com.example.medreminder.data.remote.drugapi.model.DisplayDrug
import com.example.medreminder.navigation.DetailScreenRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val drugApiService = DrugApi.retrofitService
    private val _drug = MutableStateFlow<DisplayDrug?>(null)
    val drug: StateFlow<DisplayDrug?> = _drug

    private val args = savedStateHandle.toRoute<DetailScreenRoute>()
    private val drugId: String = args.drugId

    init {
        loadDrugDetails(drugId)
    }

    private fun loadDrugDetails(drugId: String) {
        viewModelScope.launch {
            try {
                val response = drugApiService.getDrugLabels(searchQuery = "id:\"$drugId\"")
                val drug = response.results.firstOrNull()?.let { drug ->
                    DisplayDrug(
                        id = drug.id,
                        brandName = drug.openfda?.brandName?.firstOrNull(),
                        genericName = drug.openfda?.genericName?.firstOrNull(),
                        manufacturerName = drug.openfda?.manufacturerName?.firstOrNull(),
                        indicationsAndUsage = drug.indicationsAndUsage?.firstOrNull(),
                        dosageAndAdministration = drug.dosageAndAdministration?.firstOrNull(),
                        warnings = drug.warnings?.firstOrNull(),
                        adverseReactions = drug.adverseReactions?.firstOrNull()
                    )
                }
                _drug.value = drug
            } catch (e: Exception) {
                println("Detail Search Error: ${e.message}")
                _drug.value = null
            }
        }
    }
}
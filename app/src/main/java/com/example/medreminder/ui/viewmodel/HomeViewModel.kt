package com.example.medreminder.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medreminder.data.repository.IDrugRepository
import com.example.medreminder.data.remote.drugapi.model.DisplayDrug
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeViewModel(
    private val drugRepository: IDrugRepository // dependency injection
) : ViewModel() {

    private val _formattedDate = MutableStateFlow<String>("")
    val formattedDate: StateFlow<String> = _formattedDate.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<DisplayDrug>>(emptyList())
    val searchResults: StateFlow<List<DisplayDrug>> = _searchResults.asStateFlow()

    private val _favoriteDrugs = MutableStateFlow<List<DisplayDrug>>(emptyList())
    val favoriteDrugs: StateFlow<List<DisplayDrug>> = _favoriteDrugs.asStateFlow()

    init {
        loadCurrentDate()
    }

    private fun loadCurrentDate() {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
        _formattedDate.value = dateFormat.format(calendar.time)
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        if (query.isBlank()) {
            _searchResults.value = emptyList()
        } else {
            searchDrugs(query)
        }
    }

    private fun searchDrugs(query: String) {
        viewModelScope.launch {
            val formattedQuery = "openfda.brand_name:\"$query\""
            val result = drugRepository.getDrugLabels(formattedQuery, limit = 3)
            result.onFailure { e ->
                println("HomeViewModel: searchDrugs failed: ${e.message}")
                _searchResults.value = emptyList()
            }
            result.onSuccess { drugLabelResponse ->
                println("HomeViewModel: searchDrugs success with ${drugLabelResponse.results.size} results")
                val displayDrugs = drugLabelResponse.results.map { drug ->
                    DisplayDrug(
                        id = drug.id,
                        brandName = drug.openfda?.brandName?.firstOrNull()
                            .also { println("BrandName: $it") },
                        genericName = drug.openfda?.genericName?.firstOrNull()
                            .also { println("GenericName: $it") },
                        manufacturerName = drug.openfda?.manufacturerName?.firstOrNull()
                            .also { println("ManufacturerName: $it") },
                        indicationsAndUsage = drug.indicationsAndUsage?.firstOrNull()
                            .also { println("IndicationsAndUsage: $it") },
                        dosageAndAdministration = drug.dosageAndAdministration?.firstOrNull()
                            .also { println("DosageAndAdministration: $it") },
                        warnings = drug.warnings?.firstOrNull().also { println("Warnings: $it") },
                        adverseReactions = drug.adverseReactions?.firstOrNull()
                            .also { println("AdverseReactions: $it") }
                    )
                }
                _searchResults.value = displayDrugs
                println("DisplayDrugs: $displayDrugs")
            }
        }
    }

    fun toggleFavorite(drug: DisplayDrug) {
        val currentFavorites = _favoriteDrugs.value.toMutableList()
        if (currentFavorites.any { it.id == drug.id }) {
            currentFavorites.removeAll { it.id == drug.id }
        } else {
            currentFavorites.add(drug)
        }
        _favoriteDrugs.value = currentFavorites
        println("FavoriteDrugs: ${_favoriteDrugs.value}")
    }
}
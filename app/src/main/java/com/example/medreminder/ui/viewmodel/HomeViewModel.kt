package com.example.medreminder.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medreminder.data.repository.IDrugRepository
import com.example.medreminder.data.remote.drugapi.model.DisplayDrug
import com.example.medreminder.data.remote.firebase.model.DrugReminder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeViewModel(
    private val drugRepository: IDrugRepository
) : ViewModel() {

    //Aktuelles Datum für die Anzeige
    private val _formattedDate = MutableStateFlow<String>("")
    val formattedDate: StateFlow<String> = _formattedDate.asStateFlow()

    //Suchanfrage des Benutzers
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    //Suchergebnisse für die Medikamente
    private val _searchResults = MutableStateFlow<List<DisplayDrug>>(emptyList())
    val searchResults: StateFlow<List<DisplayDrug>> = _searchResults.asStateFlow()

    //Favorisierte Medikamente
    private val _favoriteDrugs = MutableStateFlow<List<DisplayDrug>>(emptyList())
    val favoriteDrugs: StateFlow<List<DisplayDrug>> = _favoriteDrugs.asStateFlow()

    //Benutzerdefinierte Medikamente
    private val _myDrugs = MutableStateFlow<List<DrugReminder>>(emptyList())
    val myDrugs: StateFlow<List<DrugReminder>> = _myDrugs.asStateFlow()

    //Fehlermeldungen
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        //Daten laden
        loadCurrentDate()
        loadFavorites()
        loadMyDrugs()
    }

    //Aktuelles Datum laden
    private fun loadCurrentDate() {
        try {
            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
            _formattedDate.value = dateFormat.format(calendar.time)
        } catch (e: Exception) {
            _errorMessage.value = "Failed to load date: ${e.message}"
        }
    }

    //Favoriten Laden (Wichtige Medikamente)
    private fun loadFavorites() {
        viewModelScope.launch {
            try {
                val result = drugRepository.getAllFavorites()
                result.onSuccess { firebaseDrugs ->
                    val displayDrugs = firebaseDrugs.mapNotNull { drug ->
                        try {
                            DisplayDrug(
                                id = drug.id,
                                brandName = drug.brandName,
                                genericName = drug.genericName ?: "",
                                manufacturerName = drug.manufacturerName ?: "",
                                indicationsAndUsage = drug.indicationsAndUsage ?: "",
                                dosageAndAdministration = drug.dosageAndAdministration ?: "",
                                warnings = drug.warnings ?: "",
                                adverseReactions = drug.adverseReactions ?: ""
                            )
                        } catch (e: Exception) {
                            _errorMessage.value = "Failed to map drug: ${e.message}"
                            null
                        }
                    }
                    _favoriteDrugs.value = displayDrugs
                    println("FavoriteDrugs: $displayDrugs")
                }.onFailure { e ->
                    println("HomeViewModel: loadFavorites failed: ${e.message}")
                    _errorMessage.value = e.message ?: "Failed to load favorites"
                    _favoriteDrugs.value = emptyList()
                }
            } catch (e: Exception) {
                println("HomeViewModel: loadFavorites exception: ${e.message}")
                _errorMessage.value = e.message ?: "Unexpected error in loadFavorites"
                _favoriteDrugs.value = emptyList()
            }
        }
    }

    private fun loadMyDrugs() {
        viewModelScope.launch {
            try {
                val result = drugRepository.getAllMyDrugs()
                result.onSuccess { firebaseDrugs ->
                    _myDrugs.value = firebaseDrugs // DrugReminder zaten doğru formatta
                    println("MyDrugs: $firebaseDrugs")
                }.onFailure { e ->
                    println("HomeViewModel: loadMyDrugs failed: ${e.message}")
                    _errorMessage.value = e.message ?: "Failed to load my drugs"
                    _myDrugs.value = emptyList()
                }
            } catch (e: Exception) {
                println("HomeViewModel: loadMyDrugs exception: ${e.message}")
                _errorMessage.value = e.message ?: "Unexpected error in loadMyDrugs"
                _myDrugs.value = emptyList()
            }
        }
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
            try {
                val formattedQuery = "openfda.brand_name:\"$query\""
                val result = drugRepository.getDrugs(formattedQuery, limit = 3)
                result.onSuccess { drugs ->
                    println("HomeViewModel: searchDrugs success with ${drugs.size} results")
                    _searchResults.value = drugs
                }.onFailure { e ->
                    println("HomeViewModel: searchDrugs failed: ${e.message}")
                    _errorMessage.value = e.message ?: "Failed to search drugs"
                    _searchResults.value = emptyList()
                }
            } catch (e: Exception) {
                println("HomeViewModel: searchDrugs exception: ${e.message}")
                _errorMessage.value = e.message ?: "Unexpected error in searchDrugs"
                _searchResults.value = emptyList()
            }
        }
    }

    fun toggleFavorite(drug: DisplayDrug) {
        viewModelScope.launch {
            try {
                val isFavorited = _favoriteDrugs.value.any { it.id == drug.id }
                if (isFavorited) {
                    val result = drugRepository.removeFavorite(drug.id)
                    result.onSuccess {
                        _favoriteDrugs.value = _favoriteDrugs.value.filter { it.id != drug.id }
                    }
                } else {
                    val result = drugRepository.addFavorite(drug)
                    result.onSuccess {
                        _favoriteDrugs.value = _favoriteDrugs.value + drug
                    }
                }
                println("FavoriteDrugs after toggle: ${_favoriteDrugs.value}")
            } catch (e: Exception) {
                println("HomeViewModel: toggleFavorite failed: ${e.message}")
                _errorMessage.value = e.message ?: "Failed to update favorite"
            }
        }
    }

    fun removeDrug(drugId: String) {
        viewModelScope.launch {
            try {
                val result = drugRepository.removeDrug(drugId)
                result.onSuccess {
                    _myDrugs.value = _myDrugs.value.filter { it.id != drugId }
                    println("Drug removed: $drugId")
                }.onFailure { e ->
                    println("HomeViewModel: removeDrug failed: ${e.message}")
                    _errorMessage.value = e.message ?: "Failed to remove drug"
                }
            } catch (e: Exception) {
                println("HomeViewModel: removeDrug exception: ${e.message}")
                _errorMessage.value = e.message ?: "Unexpected error in removeDrug"
            }
        }
    }

    fun isFavorite(drugId: String): Boolean {
        return _favoriteDrugs.value.any { it.id == drugId }
    }
}
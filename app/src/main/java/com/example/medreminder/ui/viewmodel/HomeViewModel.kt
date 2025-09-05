package com.example.medreminder.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.medreminder.data.local.Drug
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeViewModel : ViewModel() {

    private val _formattedDate = MutableStateFlow<String>("")
    val formattedDate: StateFlow<String> = _formattedDate

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _searchResults = MutableStateFlow<List<Drug>>(emptyList())
    val searchResults: StateFlow<List<Drug>> = _searchResults

    private val _favoriteDrugs = MutableStateFlow<List<Drug>>(emptyList())
    val favoriteDrugs: StateFlow<List<Drug>> = _favoriteDrugs

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        searchDrugs(query)
    }


    init {
        loadCurrentDate()
    }

    //zeigt den aktuellen Datum an nach Systemsprache deutsch 31 Dezemeber 2025
    //englisch 31 December 2025
    private fun loadCurrentDate() {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
        _formattedDate.value = dateFormat.format(calendar.time)
    }

    //bei leerer Suche wird leere Liste ausgegeben.
    private fun searchDrugs(query: String) {
        if (query.isBlank()) {
            _searchResults.value = emptyList()
            return
        }

        // Mock data -
        val mockResults = listOf(
            Drug(
                "1", "Aspirin", "",
                manufacturerName = "Aspirin",
                indicationsAndUsage = "",
                dosageAndAdministration = "100mg",
                warnings = "",
                adverseReactions = ""
            ),
            Drug(
                "2", "Vitamin D", "",
                manufacturerName = "",
                indicationsAndUsage = "",
                dosageAndAdministration = "1000IU",
                warnings = "",
                adverseReactions = ""
            ),
            Drug(
                "3", "Ibuprofen", "",
                manufacturerName = "",
                indicationsAndUsage = "",
                dosageAndAdministration = "400mg",
                warnings = "",
                adverseReactions = ""
            ),
            Drug(
                "4", "Ibuprofen", "",
                manufacturerName = "",
                indicationsAndUsage = "",
                dosageAndAdministration = "400mg",
                warnings = "",
                adverseReactions = ""
            )
            //hier wird gesucht mit der filter funktion ob
            //der gesuchte Wert innerhalb von brandname gefunden wurde
            //Groß und Kleinschreibung wird ignoriert anschließend wird
            //wird es in die Liste searchResult übertragen.
        ).filter { it.brandName.contains(query, ignoreCase = true) }

        _searchResults.value = mockResults
    }

    /*  Hier wird ein Medikament als favorit markiert
      als Parameter ein Medikament datenclasse aktuelle favoriten
     werden von der liste übertragen _favoriteDrugs
     wenn der it mit der drug id überstimmt dann heißt es es ist
     schon favorisiert dann wird die Id gelöscht(nicht mehr als Favorit markiert)
      ansonsten wird es
     in die aktuelle Liste übertragen zum schluss wird es in die
     richtige liste private übertragen und das ist dann aktuell*/

    fun toggleFavorite(drug: Drug) {
        val currentFavorites = _favoriteDrugs.value.toMutableList()
        if (currentFavorites.any { it.id == drug.id }) {
            currentFavorites.removeAll { it.id == drug.id }
        } else {
            currentFavorites.add(drug)
        }
        _favoriteDrugs.value = currentFavorites
    }
}




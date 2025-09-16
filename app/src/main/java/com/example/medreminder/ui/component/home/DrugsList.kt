package com.example.medreminder.ui.component.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medreminder.data.remote.drugapi.model.DisplayDrug

@Composable
fun DrugsList(
    drugs: List<DisplayDrug>,
    favorites: List<DisplayDrug>,
    modifier: Modifier = Modifier,
    onFavoriteClick: (DisplayDrug) -> Unit,
    onNavigateToDetail: (DisplayDrug) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(drugs) { drug ->
            DrugCard(
                drug = drug,
                isFavorite = favorites.any { it.id == drug.id },
                onFavoriteClick = { onFavoriteClick(drug) },
                onNavigateToDetail = { onNavigateToDetail(drug) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DrugListPreview() {

}
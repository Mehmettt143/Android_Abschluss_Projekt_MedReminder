package com.example.medreminder.ui.component.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medreminder.data.remote.drugapi.model.DisplayDrug

@Composable
fun FavoriteCardList(
    drugs: List<DisplayDrug>,
    modifier: Modifier = Modifier,
    onRemoveFavorite: (DisplayDrug) -> Unit,
    onNavigateToDetail: (DisplayDrug) -> Unit // Yeni navigasyon callback'i
) {
    //Lazycolumn für die Liste der favorisierten Medikamente
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(drugs) { drug ->
            //Elemente der drugs liste
            FavoriteCard(
                drug = drug,
                onFavoriteClick = onRemoveFavorite,
                onNavigateToDetail = onNavigateToDetail // Callback
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DrugListPreview() {
    //Preview für die Favoritenliste
}
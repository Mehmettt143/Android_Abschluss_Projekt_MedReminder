package com.example.medreminder.ui.component.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medreminder.data.local.Drug


@Composable
fun FavoriteCardList(
    drugs: List<Drug>,
    favorites: List<Drug>,
    modifier: Modifier = Modifier,
    onFavoriteClick: (Drug) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp)
    ) {
        items (drugs) { drug ->
            FavoriteCard(
                drug = drug,
                isFavorite = favorites.any { it.id == drug.id },
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DrugListPreview() {
    // Use Theme here
   // DrugList()
}
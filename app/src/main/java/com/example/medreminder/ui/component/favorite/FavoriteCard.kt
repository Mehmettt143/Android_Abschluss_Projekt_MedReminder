package com.example.medreminder.ui.component.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medreminder.R
import com.example.medreminder.data.remote.drugapi.model.DisplayDrug
import com.example.medreminder.ui.theme.GoldenYellowFavoriteCard
import com.example.medreminder.ui.theme.cardBorderColor

@Composable
fun FavoriteCard(
    modifier: Modifier = Modifier,
    drug: DisplayDrug,
    onFavoriteClick: (DisplayDrug) -> Unit,
    onNavigateToDetail: (DisplayDrug) -> Unit,

    ) {
    //Karte für das favorisierte Medikament
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = GoldenYellowFavoriteCard
        )
    ) {
        //Zeile für den Karteninhalt
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            //Spalte für Medikamenteninformationen
            Column(modifier = Modifier.weight(1f)) {


                Text(
                    text = stringResource(R.string.label_of_drug_brand_name),
                    color = Color.Black,
                    fontSize = 18.sp,
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = (drug.brandName ?: "Unknown"),
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )


            }
            //Info-Button für Details
            IconButton(
                onClick = { onNavigateToDetail(drug) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "info",
                    tint = cardBorderColor
                )
            }

            // Entfernen-Button für Favoriten
            IconButton(
                onClick = { onFavoriteClick(drug) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Remove from favorites",
                    tint = cardBorderColor
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteCardPreview() {
    //Preview für die Favoritenkarte

}
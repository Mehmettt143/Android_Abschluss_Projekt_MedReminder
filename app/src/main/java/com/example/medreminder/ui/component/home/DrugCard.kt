package com.example.medreminder.ui.component.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medreminder.R
import com.example.medreminder.data.remote.drugapi.model.DisplayDrug
import com.example.medreminder.ui.theme.GreenColor

@Composable
fun DrugCard(
    modifier: Modifier = Modifier,
    drug: DisplayDrug,
    isFavorite: Boolean = false,
    onFavoriteClick: (DisplayDrug) -> Unit,
    onNavigateToDetail: (DisplayDrug) -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { onNavigateToDetail(drug) },
        colors = CardDefaults.elevatedCardColors(
            containerColor = GreenColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(R.string.label_of_drug_brand_name),
                    color = Color.Black,
                    fontSize = 14.sp
                )
                Text(
                    text = drug.brandName?.uppercase() ?: stringResource(R.string.unknown_drug),
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            IconButton(onClick = { onFavoriteClick(drug) }) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Star else Icons.Default.StarOutline,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color(0xFFFFD700) else Color.Black,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DrugCardPreview() {
    // Preview için tema ekle
}
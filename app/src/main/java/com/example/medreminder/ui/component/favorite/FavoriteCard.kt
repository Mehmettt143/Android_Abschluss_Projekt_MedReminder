package com.example.medreminder.ui.component.favorite

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
import com.example.medreminder.data.local.Drug
import com.example.medreminder.ui.theme.GreenColor

@Composable
fun FavoriteCard(
    modifier: Modifier = Modifier,
    drug: Drug,
    onFavoriteClick: (Drug) -> Unit,
    isFavorite: Boolean = false
) {
    ElevatedCard(
        modifier = modifier
            .size(100.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp),
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
                    text = drug.brandName.uppercase(),
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }


        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DrugCardPreview() {
    // Use Theme here
    // DrugCard()
}
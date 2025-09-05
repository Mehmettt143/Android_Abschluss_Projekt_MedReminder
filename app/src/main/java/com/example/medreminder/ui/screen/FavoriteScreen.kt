package com.example.medreminder.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medreminder.R
import com.example.medreminder.ui.component.HeaderItem
import com.example.medreminder.ui.component.InfoTextItem
import com.example.medreminder.ui.component.favorite.FavoriteCardList

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize(),
horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ){
        HeaderItem(stringResource(R.string.label_my_favorites))
        Icon(
            painter = painterResource(R.drawable.star),
            contentDescription = "drug",
            modifier.size(84.dp),
            tint = Color.Unspecified
        )
        InfoTextItem(stringResource(R.string.label_for_info_favorite_screen), textAlign = TextAlign.Center)

        FavoriteCardList(
            drugs =emptyList() ,
            favorites = emptyList()
        ) {


            
        }


    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteScreenPreview() {
    // Use Theme here
    FavoriteScreen()
}
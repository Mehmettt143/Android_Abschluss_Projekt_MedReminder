package com.example.medreminder.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medreminder.R
import com.example.medreminder.navigation.DetailScreenRoute
import com.example.medreminder.ui.component.HeaderItem
import com.example.medreminder.ui.component.InfoTextItem
import com.example.medreminder.ui.component.favorite.FavoriteCardList
import com.example.medreminder.ui.viewmodel.FavoriteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = koinViewModel(),
    navController: NavController,
) {
    val favoriteDrugs by viewModel.favoriteDrugs.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    //Layout des Favoritenscreens
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        //Titel Wichtige Medikamente
        HeaderItem(stringResource(R.string.label_my_favorites))
        Icon(
            painter = painterResource(R.drawable.star),
            contentDescription = "star",
            modifier = Modifier.size(84.dp),
            tint = Color.Unspecified
        )
        //Infotext über die Funktion des Bildschirms
        InfoTextItem(
            stringResource(R.string.label_for_info_favorite_screen),
            textAlign = TextAlign.Center
        )
        //Ladeanzeige oder Favoritenliste anzeigen
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            //List der favorisiserten Medikamente
            FavoriteCardList(
                drugs = favoriteDrugs,
                onRemoveFavorite = { displayDrug ->
                    viewModel.removeFavorite(displayDrug.id)
                },
                onNavigateToDetail = { displayDrug ->
                    navController.navigate(DetailScreenRoute(drugId = displayDrug.id))

                }
            )
        }
    }
}
package com.example.medreminder.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.medreminder.R
import com.example.medreminder.navigation.DetailScreenRoute
import com.example.medreminder.ui.component.HeaderItem
import com.example.medreminder.ui.component.home.DrugSearchBar
import com.example.medreminder.ui.component.home.DrugsList
import com.example.medreminder.ui.component.home.MyDrugsList
import com.example.medreminder.ui.theme.LogoutIconColor
import com.example.medreminder.ui.viewmodel.AuthViewModel
import com.example.medreminder.ui.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    navController: NavController,
    authViewModel: AuthViewModel = koinViewModel()
) {
    //Layout des Homescreens
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val date = viewModel.formattedDate.collectAsState("")
        val searchQuery = viewModel.searchQuery.collectAsState("")
        val searchResults = viewModel.searchResults.collectAsState(emptyList())
        val favoriteDrugs = viewModel.favoriteDrugs.collectAsState(emptyList())
        val myDrugs = viewModel.myDrugs.collectAsState(emptyList())
        val username = authViewModel.username.collectAsState("")


        //Titel und Logoutbutton
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            HeaderItem(
                stringResource(R.string.label_medreminder),

                )


            IconButton(
                onClick = { authViewModel.logout() },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    Icons.Filled.Logout,
                    contentDescription = "Logout",
                    tint = LogoutIconColor
                )
            }
        }

        //Aktuelles Datum anzeigen
        Text(
            text = stringResource(R.string.label_date_of_today, date.value),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        //Suchleiste für Medikamente
        DrugSearchBar(
            searchQuery = searchQuery.value,
            onSearchQueryChange = { viewModel.updateSearchQuery(it) },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        //Suchergebnisse anzeigen
        if (searchResults.value.isNotEmpty()) {
            DrugsList(
                drugs = searchResults.value,
                favorites = favoriteDrugs.value,
                onFavoriteClick = { drug ->
                    viewModel.toggleFavorite(drug)
                },
                onNavigateToDetail = { drug ->
                    navController.navigate(DetailScreenRoute(drugId = drug.id))
                },
                modifier = Modifier.weight(0.5f)
            )
        } else if (searchQuery.value.isNotEmpty()) {
            // keine Medikament gefunden
            Text(
                text = stringResource(R.string.label_for_dont_find_drug_text),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(32.dp)
            )
        }
        //Benutzerdefinierte Medikamente anzeigen
        HeaderItem(stringResource(R.string.my_drugs, username.value))

        Icon(
            painter = painterResource(R.drawable.drug),
            contentDescription = "drug",
            modifier = Modifier.size(84.dp),
            tint = Color.Unspecified
        )
        MyDrugsList(
            modifier = Modifier.weight(0.5f),
            myDrugs = myDrugs.value,
            onRemoveClick = { drug ->
                viewModel.removeDrug(drug.id)
            }
        )


    }
}


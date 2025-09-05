package com.example.medreminder.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medreminder.R
import com.example.medreminder.ui.component.HeaderItem
import com.example.medreminder.ui.component.home.DrugSearchBar
import com.example.medreminder.ui.component.home.DrugsList
import com.example.medreminder.ui.viewmodel.HomeViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val date = viewModel.formattedDate.collectAsState("")

        val searchQuery = viewModel.searchQuery.collectAsState("")
        val searchResults = viewModel.searchResults.collectAsState(emptyList())
        val favoriteDrugs = viewModel.favoriteDrugs.collectAsState(emptyList())


        HeaderItem(stringResource(R.string.label_medreminder))
        //Datumsanzeige
        Text(
            text = stringResource(R.string.label_date_of_today, date.value),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )

        DrugSearchBar(
            searchQuery = searchQuery.value,
            onSearchQueryChange = { viewModel.updateSearchQuery(it) },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Suchergebnisse
        if (searchResults.value.isNotEmpty()) {
            DrugsList(
                drugs = searchResults.value,
                favorites = favoriteDrugs.value,
                onFavoriteClick = { drug ->
                    viewModel.toggleFavorite(drug)
                },
                modifier = Modifier.weight(1f)
            )
        } else if (searchQuery.value.isNotEmpty()) {
            // keine Medikamente gefunden
            Text(
                text = stringResource(R.string.label_for_dont_find_drug_text),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(32.dp)
            )
        }



        HeaderItem(stringResource(R.string.my_drugs))

        Icon(
            painter = painterResource(R.drawable.drug),
            contentDescription = "drug",
            modifier.size(84.dp),
            tint = Color.Unspecified
        )


    }


}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    // Use Theme here
    HomeScreen()
}
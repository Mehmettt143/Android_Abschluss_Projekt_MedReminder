package com.example.medreminder.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medreminder.R
import com.example.medreminder.ui.component.HeaderItem
import com.example.medreminder.ui.component.InfoTextItem
import com.example.medreminder.ui.component.detail.DrugDetailCard
import com.example.medreminder.ui.viewmodel.DetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = koinViewModel(),
    navController: NavController,
) {
    val drug = viewModel.drug.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value
    val errorMessage = viewModel.errorMessage.collectAsState().value


    //Zurück-Button und Titel
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(modifier = Modifier) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.left_arrow),
                    contentDescription = "left_arrow_icon",
                    tint = Color.Unspecified
                )
            }
            HeaderItem(stringResource(R.string.medicine_information_sheet))
        }
        //Ladezustand und Fehler oder Medikamentendetail anzeigen
        when {
            isLoading -> {
                InfoTextItem(
                    title = "Loading...",
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }

            errorMessage != null -> {
                //Fehlermeldung anzeigen
                InfoTextItem(
                    title = errorMessage,
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }

            drug == null -> {
                //Kein Medikament gefunden
                InfoTextItem(
                    title = stringResource(id = R.string.label_for_dont_find_drug_text),
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }

            else -> {
                //Medikamentdetails anzeigen
                DrugDetailCard(drug = drug)


            }
        }
    }
}

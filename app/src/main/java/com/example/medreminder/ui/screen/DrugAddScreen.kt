package com.example.medreminder.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.example.medreminder.R
import com.example.medreminder.ui.component.HeaderItem
import com.example.medreminder.ui.component.InfoTextItem
import com.example.medreminder.ui.viewmodel.DrugAddViewModel
import com.example.medreminder.ui.component.drugadd.FormTextInput
import com.example.medreminder.ui.component.drugadd.SaveDrug
import com.example.medreminder.ui.component.drugadd.TimeOfDaySelector
import com.example.medreminder.ui.component.drugadd.TimePickerInput
import com.example.medreminder.ui.theme.CardBackgroundLightBlue
import org.koin.androidx.compose.koinViewModel


@Composable
fun DrugAddScreen(
    modifier: Modifier = Modifier,
    viewModel: DrugAddViewModel = koinViewModel()
) {
    // UI-States für Benutzereingaben
    val medicationName by viewModel.drugName.collectAsState()
    val dosage by viewModel.drugDosis.collectAsState()
    val selectedTime by viewModel.selectedTime.collectAsState()
    val timeHour by viewModel.timeHour.collectAsState()
    val timeMinute by viewModel.timeMinute.collectAsState()
    val saveSuccess by viewModel.saveSuccess.collectAsState()


    // Erfolgsdialog anzeigen
    if (saveSuccess) {
        AlertDialog(
            onDismissRequest = { viewModel.resetSaveSuccess() },
            title = { Text(stringResource(R.string.title_saving)) },
            text = { Text(stringResource(R.string.success_save_text)) },
            icon = { Icon(imageVector = Icons.Default.Info, contentDescription = "info") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.resetSaveSuccess()
                    viewModel.clearForm()
                }) {
                    Text(stringResource(R.string.ok_button))
                }
            }
        )
    }

    //Layout des Bildschirms
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        //Titel Medikament hinzufügen
        HeaderItem(stringResource(R.string.label_for_drug_add))
        Icon(
            painter = painterResource(R.drawable.drug),
            contentDescription = "drug",
            modifier.size(84.dp),
            tint = Color.Unspecified
        )
        //Eingabeformular mit Scroll-Funktion
        Column(modifier.verticalScroll(rememberScrollState())) {

            //Infotext über die Funktion des Bildschirms
            InfoTextItem(
                stringResource(R.string.text_for_new_information_drug),
                textAlign = TextAlign.Center
            )

            //Eingabefeld für Medikamentenname
            FormTextInput(
                value = medicationName,
                onValueChange = { newValue -> viewModel.updateDrugName(newValue) },
                label = stringResource(R.string.label_name),
                placeholder = stringResource(R.string.example_aspirin_placeholder),
                title = stringResource(R.string.label_brandname),
                maxLength = 50,
                validation = { newValue -> newValue.all { char -> char.isLetter() } } // es können keine Zahl + Text kombinationen vorkommen
                //alle Buchstaben werden überprüft Buchstabe oder nicht wenn Zahl oder Abstand kommt es wird false rauskommen
            )
            //Eingabefeld für Dosierung
            FormTextInput(
                value = dosage,
                onValueChange = { newValue -> viewModel.updateDrugDosis(newValue) },
                label = stringResource(R.string.label_dosis),
                placeholder = stringResource(R.string.placeholder_drug_dosis),
                title = stringResource(R.string.label_dosis_input),
                maxLength = 10,
            )
            //Karte für Tageszeit-Auswahl
            ElevatedCard(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = CardBackgroundLightBlue
                ),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    //Tageszeit Auswahl
                    TimeOfDaySelector(
                        selectedTime = selectedTime,
                        onTimeSelected = { newTime ->
                            viewModel.updateSelectedTime(newTime)
                        }
                    )
                }
            }
            //Eingabefeld für Uhrzeit
            TimePickerInput(
                hour = timeHour,
                minute = timeMinute,
                onTimeChanged = { hour, minute ->
                    viewModel.updateTimeHour(hour)
                    viewModel.updateTimeMinute(minute)
                },
                title = stringResource(R.string.label_time)
            )

            SaveDrug(
                onClick = {
                    viewModel.saveDrug(
                        medicationName = medicationName,
                        dosage = dosage,
                        selectedTime = selectedTime,
                        timeHour = timeHour,
                        timeMinute = timeMinute
                    )
                },
                enabled = medicationName.isNotEmpty() && dosage.isNotEmpty() && selectedTime.isNotEmpty() && timeHour.isNotEmpty() && timeMinute.isNotEmpty()
            )
        }
        //Abstand
        Spacer(modifier = Modifier.height(8.dp))
    }
}

/*          TimePickerInput logik
           * hier wird die Stundenfeld und Minutenfeld ermittelt hour minute wird von der
           * State übernommen sobald der Benutzer eine Minute Stunde angibt timeInnput wird es dort in der
           * onvaluechange weitergegeben, angenommen 08 dann wird es in der TimeInput innerhalb TimepickerInput
           * der neue wert validiert und wir bekommen es in onTimeChanged den neuen Wert für Minute und Stunde
           * */



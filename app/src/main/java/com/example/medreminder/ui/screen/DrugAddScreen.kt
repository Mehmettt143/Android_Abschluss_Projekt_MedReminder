package com.example.medreminder.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.medreminder.R
import com.example.medreminder.ui.component.HeaderItem
import com.example.medreminder.ui.component.InfoTextItem
import com.example.medreminder.ui.viewmodel.DrugAddViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medreminder.ui.component.drugadd.FormTextInput
import com.example.medreminder.ui.component.drugadd.SaveDrug
import com.example.medreminder.ui.component.drugadd.TimeOfDaySelector
import com.example.medreminder.ui.component.drugadd.TimePickerInput
import com.example.medreminder.ui.theme.CardBackgroundLightBlue


@Composable
fun DrugAddScreen(
    modifier: Modifier = Modifier,
    viewModel: DrugAddViewModel = viewModel()
) {

    var medicationName by remember { mutableStateOf("") }
    var dosage by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    var timeHour by remember { mutableStateOf("08") }
    var timeMinute by remember { mutableStateOf("00") }


    val saveSuccess by viewModel.saveSuccess.collectAsState()


    // Alert beim Erfolg
    if (saveSuccess) {
        AlertDialog(
            onDismissRequest = { viewModel.resetSaveSuccess() },
            title = { Text(stringResource(R.string.title_saving)) },
            text = { Text(stringResource(R.string.success_save_text)) },
            confirmButton = {
                TextButton(onClick = { viewModel.resetSaveSuccess() }) {
                    Text("OK")
                }
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HeaderItem(stringResource(R.string.label_for_drug_add))
        Icon(
            painter = painterResource(R.drawable.drug),
            contentDescription = "drug",
            modifier.size(84.dp),
            tint = Color.Unspecified
        )
        InfoTextItem(
            stringResource(R.string.text_for_new_information_drug),
            textAlign = TextAlign.Center
        )

        FormTextInput(
            value = medicationName,
            onValueChange = { medicationName = it },
            label = stringResource(R.string.label_name),
            placeholder = stringResource(R.string.example_aspirin_placeholder),
            title = stringResource(R.string.label_brandname)
        )
        FormTextInput(
            value = dosage,
            onValueChange = { dosage = it },
            label = stringResource(R.string.label_dosis),
            placeholder = stringResource(R.string.placeholder_drug_dosis),
            title = stringResource(R.string.label_dosis_input)
        )
        ElevatedCard(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = CardBackgroundLightBlue
            ),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                TimeOfDaySelector(
                    selectedTime = selectedTime,
                    onTimeSelected = { selectedTime = it }
                )
            }
        }
        TimePickerInput(
            hour = timeHour,
            minute = timeMinute,
            onTimeChanged = { hour, minute ->
                timeHour = hour
                timeMinute = minute
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
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun AddScreenPreview() {
    DrugAddScreen()
}
package com.example.medreminder.ui.component.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medreminder.R


@Composable
fun TimeBeforeNotificationSelector(
    selectedTime: String,
    onTimeSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    options: List<String> = listOf(
        stringResource(R.string.label_5_minutes),
        stringResource(R.string.label_15_minutes),
        stringResource(R.string.label_30_minutes)
    )
) {
    //Zeile für die Zeitoptionsauswahl
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        //Prüft ob Optionen vorhanden sind
        if (options.isNotEmpty()) {
            options.forEach { option ->
                //Button für jede Zeitoption
                TimeOptionButton(
                    text = option,
                    isSelected = selectedTime == option,
                    onClick = { onTimeSelected(option) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

// Preview für Beispiel Optionen
@Preview(showBackground = true, name = "Example Options")
@Composable
private fun TimeBeforeNotificationSelectorEmptyPreview() {
    TimeBeforeNotificationSelector(
        selectedTime = "5 Minuten",
        onTimeSelected = {},
        options = listOf(
            "5 Minuten",
            "15 Minuten",
            "30 Minuten"
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
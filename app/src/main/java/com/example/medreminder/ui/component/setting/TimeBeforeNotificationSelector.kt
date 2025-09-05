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
import com.example.medreminder.ui.component.drugadd.TimeOptionButton


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
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        options.forEach { option ->
            TimeOptionButton(
                text = option,
                isSelected = selectedTime == option,
                onClick = { onTimeSelected(option) },
                modifier = Modifier.weight(1f)
            )
        }
    }

}




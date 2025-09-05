package com.example.medreminder.ui.component.drugadd

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medreminder.R
import com.example.medreminder.ui.theme.BottombarBlue
import com.example.medreminder.utils.TimeHelper


@Composable
fun TimePickerInput(
    hour: String,
    minute: String,
    onTimeChanged: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.label_time)
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            color = BottombarBlue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp, start = 8.dp)
        )

        // Uhrzeit
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            // Stunden input
            TimeInput(
                value = hour,
                onValueChange = { newValue ->
                    val validatedValue = TimeHelper.validateTimeInput(newValue, 0..23)
                    onTimeChanged(validatedValue, minute)
                },
                label = stringResource(R.string.label_hour),
                placeholder = "08",
                modifier = Modifier.weight(1f)
            )

            Text(
                text = ":",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            // Minuten Input
            TimeInput(
                value = minute,
                onValueChange = { newValue ->
                    val validatedValue = TimeHelper.validateTimeInput(newValue, 0..59)
                    onTimeChanged(hour, validatedValue)
                },
                label = stringResource(R.string.label_minute),
                placeholder = "00",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TimePickerInputPreview() {
    // Use Theme here
    // TimePickerInput()
}
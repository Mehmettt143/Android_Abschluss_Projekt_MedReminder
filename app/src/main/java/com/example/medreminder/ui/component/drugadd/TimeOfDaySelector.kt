package com.example.medreminder.ui.component.drugadd

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medreminder.R
import com.example.medreminder.ui.theme.BottombarBlue


@Composable
fun TimeOfDaySelector(
    selectedTime: String,
    onTimeSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.label_time_of_drug),

    options: List<String> = listOf(
        stringResource(R.string.label_mornings),
        stringResource(R.string.label_pm),
        stringResource(R.string.label_afternoon)
    )
) {
    Column(modifier = modifier) {
      //Titel
        Text(
            text = title,
            color = BottombarBlue,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp, start = 8.dp)
        )

        // Buttons
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
}


// Preview
@Preview(showBackground = true)
@Composable
private fun TimeOfDaySelectorPreview() {
    TimeOfDaySelector(
        selectedTime = "morgens",
        onTimeSelected = {}
    )
}
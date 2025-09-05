package com.example.medreminder.ui.component.drugadd

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
 fun TimeInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        textStyle =TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun TimeInputPreview() {
    // Use Theme here
    //TimeInput()
}
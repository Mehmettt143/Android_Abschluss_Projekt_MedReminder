package com.example.medreminder.ui.component.drugadd

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import com.example.medreminder.ui.theme.BottombarBlue


@Composable
fun FormTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    title: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    maxLength: Int? = null,
    validation: ((String) -> Boolean)? = null
) {
    Column(modifier = modifier) {
        title?.let {
            Text(
                text = it,
                color = BottombarBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->

                if (maxLength == null || newValue.length <= maxLength) {
                    if (validation == null || validation(newValue)) {
                        onValueChange(newValue)
                    }
                }
            },
            label = { Text(label) },
            placeholder = { Text(placeholder) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = singleLine,
            shape = RoundedCornerShape(25.dp),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
        )
    }
}


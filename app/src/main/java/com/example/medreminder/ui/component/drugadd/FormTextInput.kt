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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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
    //Spalte für das Eingabeformular
    Column(modifier = modifier) {
        //wenn Titel angegeben wurde dann wird es ausgeführt und ein Titel wird erstellt ansonsten wird übersprungen
        title?.let {
            Text(
                text = it,
                color = BottombarBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        //Umrandetes Textfeld für die Eingabe
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                //Überprüfung der maximalen Länge und Validierung
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
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FormTextInputPreview() {
    FormTextInput(
        value = "Aspirin",
        onValueChange = {},
        label = "Name",
        placeholder = "Medikamentenname eingeben",
        title = "Medikament-Bezeichnung",
        keyboardType = KeyboardType.Text
    )
}

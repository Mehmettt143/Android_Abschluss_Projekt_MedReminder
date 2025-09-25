package com.example.medreminder.ui.component.auth

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.medreminder.R
import com.example.medreminder.ui.theme.BottombarBlue

@Composable
fun UsernameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    //Umrandetes Textfeld für die Eingabe des Benutzernamens
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.username_label)) }, // label für Benutzernamen
        //Symbol für Benutzer
        trailingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next, //wechselt zum nächsten Eingabefeld
            keyboardType = KeyboardType.Text //Nur Texteingabe
        ),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BottombarBlue, //blaue Umrandung bei Fokus
            errorBorderColor = Color.Red, //Rote Umrandung bei Fehler
            focusedTrailingIconColor = BottombarBlue, //Blaues Symbol bei Fokus
            focusedTextColor = Color.Black, //Textcolor  bei Fokus
            focusedLabelColor = BottombarBlue //Blauer Label bei Fokus

        ),

        modifier = modifier
    )


}
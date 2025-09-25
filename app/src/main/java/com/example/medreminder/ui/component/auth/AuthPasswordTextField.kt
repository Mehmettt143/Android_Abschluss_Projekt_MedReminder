package com.example.medreminder.ui.component.auth

import android.R.attr.maxLength
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.medreminder.R
import com.example.medreminder.ui.theme.BottombarBlue

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false
) {
    //Zustand für die Sichtbarkeit des Passworts
    var passwordVisible by remember { mutableStateOf(false) }

    //Umrandetes Textfeld für die Passworteingabe
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.password_label)) }, // Label für Passwort
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password, // Passwort-Tastatur
            imeAction = ImeAction.Done // Aktion "Fertig" für die Tastatur
        ),
        singleLine = true,
        isError = isError, // Fehlerzustand anzeigen
        trailingIcon = {
            // Button zum Umschalten der Passwortsichtbarkeit
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = if (passwordVisible) stringResource(R.string.password_visibility_on) else stringResource(
                        R.string.password_visibility_off
                    ),
                    tint = if (isError) Color.Red else Color.DarkGray // Farbe je nach Fehlerzustand
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BottombarBlue, // Blaue Umrandung bei Fokus
            errorBorderColor = Color.Red, // Rote Umrandung bei Fehler
            focusedTrailingIconColor = BottombarBlue, // Blaues Symbol bei Fokus
            focusedTextColor = Color.Black, // Blauer Text bei Fokus
            focusedLabelColor = BottombarBlue, // Blaues Label bei Fokus
            errorTrailingIconColor = Color.Red // Rotes Symbol bei Fehler
        ),

        )


}

// Preview für das Passwort-Eingabefeld
@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview() {
    PasswordTextField(
        value = "pass123",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        isError = false
    )
}
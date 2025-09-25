package com.example.medreminder.ui.component.auth


import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
fun EmailTextField(
    emailText: String,
    onEmailTextChange: (String) -> Unit,
    showSupportingText: Boolean,
    modifier: Modifier = Modifier,
    errorMessage: String = stringResource(R.string.email_format_error)

) {
    //Umrandetes Textfeld für die E-Mail-Eingabe
    OutlinedTextField(
        value = emailText,
        onValueChange = onEmailTextChange,
        label = { Text(stringResource(R.string.email_label)) },
        trailingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "e-mail") },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next, //wechselt zum nächsten Eingabefeld
            keyboardType = KeyboardType.Email,
        ),
        singleLine = true,
        supportingText = {
            if (showSupportingText) Text(errorMessage)
        },
        isError = showSupportingText,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BottombarBlue,
            errorBorderColor = Color.Red,
            focusedTrailingIconColor = BottombarBlue,
            focusedTextColor = Color.Black,
            focusedLabelColor = BottombarBlue

        ),
        modifier = modifier,

        )


}
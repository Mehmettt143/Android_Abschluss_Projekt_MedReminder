package com.example.medreminder.ui.component

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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.medreminder.ui.theme.BottombarBlue

@Composable
fun UsernameTextField(
    value: String,
    onValueChange: (String) -> Unit,
//    showSupportingText: Boolean,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Username:") },
        trailingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BottombarBlue,
            errorBorderColor = Color.Red,
            focusedTrailingIconColor = BottombarBlue,
            focusedTextColor = BottombarBlue,
            focusedLabelColor = BottombarBlue

        ),
//        supportingText = { if (showSupportingText)
//            Text("Die Email muss in einem gültigen Format sein, Bsp jemand@bsp.de")
//        },
//        isError = showSupportingText,
        modifier = modifier
    )



}
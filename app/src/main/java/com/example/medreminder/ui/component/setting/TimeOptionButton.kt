package com.example.medreminder.ui.component.setting

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medreminder.ui.theme.TimeOptionColorFalse
import com.example.medreminder.ui.theme.TimeOptionColorTrue

@Composable
fun TimeOptionButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    textColor: Color = Color.Black,
    fontSize: Int = 10,
    modifier: Modifier = Modifier
) {
    //Button für die Zeitoption
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = if (isSelected) {
            ButtonDefaults.buttonColors(TimeOptionColorTrue)
        } else {
            ButtonDefaults.buttonColors(TimeOptionColorFalse)
        }
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else textColor,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// Preview für den Button (ausgewählt)
@Preview(showBackground = true, name = "Selected")
@Composable
private fun TimeOptionButtonSelectedPreview() {
    TimeOptionButton(
        text = "Morgens",
        isSelected = true,
        onClick = {},
        textColor = Color.Black,
        fontSize = 14,
        modifier = Modifier.fillMaxWidth()
    )
}
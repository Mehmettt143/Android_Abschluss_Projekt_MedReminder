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
    modifier: Modifier = Modifier
) {
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
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TimeOptionButtonPreview() {
    // Use Theme here
    // TimeOptionButton()
}
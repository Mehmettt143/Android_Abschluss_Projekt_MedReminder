package com.example.medreminder.ui.component.drugadd

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
import com.example.medreminder.ui.theme.BottombarBlue
import com.example.medreminder.ui.theme.DisabledSaveDrugColor

@Composable
fun TimeOptionButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    //Button für die Auswahl einer Tageszeit
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = if (isSelected) {
            //Blau für ausgewählten Zustand
            ButtonDefaults.buttonColors(BottombarBlue)
        } else {
            //Zustand bei nicht selektiert andere Farbe
            ButtonDefaults.buttonColors(DisabledSaveDrugColor)
        }
    ) {
        //Text im Button
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Black,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TimeOptionButtonPreview() {
    //Preview für den Zeitoptions-Button
    TimeOptionButton(
        text = "Morgens",
        isSelected = true,
        onClick = {}
    )
}
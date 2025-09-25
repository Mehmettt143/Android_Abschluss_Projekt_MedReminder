package com.example.medreminder.ui.component.drugadd

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medreminder.R
import com.example.medreminder.ui.theme.DisabledSaveDrugColor
import com.example.medreminder.ui.theme.SaveDrugColor

@Composable
fun SaveDrug(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String = stringResource(R.string.text_save),
    backgroundColor: Color = SaveDrugColor
) {
    //Button zum Speichern eines Medikaments
    Button(
        onClick = onClick,
        enabled = enabled, // Aktiviert oder Deaktiviert basierend auf Eingaben
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) backgroundColor else DisabledSaveDrugColor //Farbe abhängig vom Zustand
        ),
        shape = RoundedCornerShape(12.dp) //abgerundete Ecken
    ) {
        //Zeile für den Button-Inhalt
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Text im Button
            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

//Preview für den Speicherbutton
@Preview(showBackground = true)
@Composable
private fun SaveDrugPreview() {
    SaveDrug(onClick = {})
}
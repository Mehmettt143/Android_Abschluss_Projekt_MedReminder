package com.example.medreminder.ui.component.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.example.medreminder.R
import com.example.medreminder.ui.component.HeaderItem
import com.example.medreminder.ui.component.InfoTextItem
import com.example.medreminder.ui.theme.BottombarBlue
import com.example.medreminder.ui.theme.SettingsScreenCardColor


@Composable
fun PdfExportCard(
    onExportClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    //Karte für die PDF-Export-Option
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = SettingsScreenCardColor
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        //Spalte für den Inhalt der Karte
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            HeaderItem(stringResource(R.string.label_export), 22)
            InfoTextItem(
                stringResource(R.string.infotext_export), 16, Color.Black, TextAlign.Center,
                TextDecoration.Underline
            )
            //Abstand allgemein
            Spacer(modifier.padding(16.dp))
            // Export-Button
            Button(
                onClick = onExportClicked,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BottombarBlue
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                //Export Icon
                Icon(
                    painter = painterResource(R.drawable.export),
                    contentDescription = "pdf",
                    tint = Color.Unspecified,
                )
                //Export-Button Text
                InfoTextItem(
                    stringResource(R.string.button_lable_export), 18, color = Color.White,
                    textAlign = TextAlign.Center
                )

            }
        }
    }
}

//Preview für die PDF-Export-Karte
@Preview(showBackground = true)
@Composable
private fun PdfExportCardPreview() {
    PdfExportCard(
        onExportClicked = {},
        modifier = Modifier.fillMaxWidth()
    )
}
package com.example.medreminder.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.medreminder.R
import com.example.medreminder.ui.component.HeaderItem
import com.example.medreminder.ui.component.InfoTextItem
import com.example.medreminder.ui.component.setting.NotificationSettings
import com.example.medreminder.ui.component.setting.PdfExportCard
import com.example.medreminder.ui.component.setting.TimeBeforeNotificationSelector
import com.example.medreminder.ui.theme.SettingsScreenCardColor


@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onNotificationTimeSelected: (Int) -> Unit = {},
    onExportPdfClicked: () -> Unit = {},
    currentNotificationTime: Int = 30
) {
    var selectedTime by remember { mutableStateOf("") }
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {

        HeaderItem(stringResource(R.string.label_for_settings))
        Icon(
            painter = painterResource(R.drawable.settings),
            contentDescription = "drug",
            modifier.size(84.dp),
            tint = Color.Unspecified
        )
        InfoTextItem(
            stringResource(R.string.text_for_settings_reminder_info),
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )

        ElevatedCard(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = SettingsScreenCardColor
            ),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
        ) {
            //Einstellungen
            NotificationSettings(
                currentNotificationTime = currentNotificationTime,
                onTimeSelected = onNotificationTimeSelected,
                modifier = Modifier.padding(bottom = 16.dp)
            )


            TimeBeforeNotificationSelector(
                selectedTime = selectedTime,
                onTimeSelected = { selectedTime = it }
            )
        }




        // PDF Export Card
        PdfExportCard(
            onExportClicked = onExportPdfClicked
        )

        Spacer(modifier = Modifier.weight(1f))


    }


}


/*
@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    // Use Theme here
    SettingsScreen()
}*/

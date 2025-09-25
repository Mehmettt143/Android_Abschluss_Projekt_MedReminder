package com.example.medreminder.ui.component.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medreminder.R
import com.example.medreminder.ui.component.HeaderItem
import com.example.medreminder.ui.component.InfoTextItem
import com.example.medreminder.ui.theme.SettingsScreenCardColor

@SuppressLint("SuspiciousIndentation")
@Composable
fun NotificationSettingsCard(
    currentNotificationTime: String,
    onTimeSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    //Benachrichtigungseinstellungen Karte
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = SettingsScreenCardColor
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        // Spalte für die Benachrichtigungseinstellungen
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Card Überschrift
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(R.drawable.bell),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                HeaderItem(stringResource(R.string.label_for_notification), 20)
            }

            //Informationstext
            InfoTextItem(
                stringResource(R.string.infotext_for_notification),
                16,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline
            )
            Spacer(modifier = Modifier.padding(8.dp))

            //Zeit vor Benachrichtigung auswählen
            TimeBeforeNotificationSelector(
                selectedTime = currentNotificationTime,//aktuell ""
                onTimeSelected = onTimeSelected
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotificationSettingCardPreview() {
    NotificationSettingsCard(
        currentNotificationTime = "30 Minuten",
        onTimeSelected = {}
    )
}
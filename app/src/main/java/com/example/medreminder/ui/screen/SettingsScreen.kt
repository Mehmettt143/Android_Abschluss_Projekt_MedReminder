package com.example.medreminder.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.medreminder.R
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medreminder.ui.component.HeaderItem
import com.example.medreminder.ui.component.setting.NotificationSettingsCard
import com.example.medreminder.ui.component.setting.PdfExportCard
import com.example.medreminder.ui.theme.LogoutIconColor
import com.example.medreminder.ui.viewmodel.AuthViewModel
import com.example.medreminder.ui.viewmodel.SettingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onExportPdfClicked: () -> Unit = {},
    authViewmodel: AuthViewModel = koinViewModel(),
    viewModel: SettingViewModel = viewModel()
) {
    val selectedTime by viewModel.notificationTime.collectAsState()

    //Layout für Settingsscreen
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        //Titel Einstellungen
        HeaderItem(stringResource(R.string.label_for_settings))
        Icon(
            painter = painterResource(R.drawable.settings),
            contentDescription = "settings",
            modifier = Modifier.size(64.dp),
            tint = Color.Unspecified
        )

        //Abmelden-Button
        Button(
            onClick = { authViewmodel.logout() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(LogoutIconColor)
        ) {
            Icon(
                Icons.Default.Logout,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            Text("Logout")
        }

        //Benachrichtigungseinstellungen Karte
        /*
*selectedTime ist der aktuelle Zeit die ausgewählt ist im Moment als Default leerer String
*  onTimeSelected hier wird der neue Wert in Viewmodel gespeichert in einer mutableState anschließend
* wird der Wert collectet der neue wert es wird recomposition durchgeführt alle Buttons werden neu erstellt
* und der neue Wert wird selektiert.
* */
        NotificationSettingsCard(
            currentNotificationTime = selectedTime,//in der state viewmodel gespeicherte Wert aktuelle Wert den neuen Wert bekommen wir hier
            onTimeSelected = { newTime -> //wenn eine neue option ausgewählt wird wird es in viewmodel aktualisiert neuer wert
                viewModel.updateNotificationTime(newTime)

            }
        )

        // PDF Export Karte
        PdfExportCard(
            onExportClicked = onExportPdfClicked
        )

        //Abstand
        Spacer(modifier = Modifier.weight(1f))
    }
}



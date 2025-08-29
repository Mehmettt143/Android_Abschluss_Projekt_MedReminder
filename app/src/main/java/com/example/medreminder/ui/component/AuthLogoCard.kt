package com.example.medreminder.ui.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medreminder.R
import com.example.medreminder.ui.theme.BottombarBlue


@Composable
fun AuthLogoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()


    ) {
        Column(
            modifier = Modifier
                .background(BottombarBlue)
                .fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            // Bild (Platzhalter, ersetze R.drawable.dein_bild mit deiner Bildressource)
            Image(
                painter = painterResource(id = R.drawable.logo_round),
                contentDescription = "logo",
                modifier = Modifier
                    .padding(top = 50.dp)
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "MedReminder",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Ihre Gesundheit. Ihre Kontrolle",
                color = Color.White, // Weißer Text für Kontrast
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AuthLogoCardPreview() {
    AuthLogoCard()

}
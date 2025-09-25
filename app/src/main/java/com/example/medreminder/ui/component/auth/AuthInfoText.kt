package com.example.medreminder.ui.component.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AuthInfoText(
    text: String,
    fontSize: Int,
    color: Color,
    modifier: Modifier = Modifier
) {
    //Zeile für den Informationstext und den Icon
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //Informationstext
        Text(
            text = text,
            color = color,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier
                .weight(1f)
                .padding(top = 8.dp)
        )
        //check-icon
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "check",
            tint = Color(0xFF4CAF50),
            modifier = Modifier
                .size(18.dp)
                .align(Alignment.CenterVertically)
                .padding(end = 8.dp)
        )
    }
}

//Preview für den Informationstext
@Preview(showBackground = true)
@Composable
private fun InfoSectionPreview() {
    AuthInfoText(
        text = "Mindestens 8 Zeichen erforderlich",
        fontSize = 16,
        color = Color.Black,
        modifier = Modifier.fillMaxWidth()
    )
}


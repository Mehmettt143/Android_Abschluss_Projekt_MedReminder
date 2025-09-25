package com.example.medreminder.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import com.example.medreminder.ui.theme.BottombarBlue

@Composable
fun HeaderItem(
    title: String,
    size: Int = 27,
    color: Color = BottombarBlue,
    modifier: Modifier = Modifier
) {
    //Box für die Überschrift
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.TopCenter
    ) {

        //Text für die Überschrift
        Text(
            text = title,
            fontSize = size.sp,
            style = MaterialTheme.typography.titleLarge,
            color = color,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }


}

//Preview für Überschrift
@Preview(showBackground = true)
@Composable
private fun AppNameHeaderItemPreview() {

    HeaderItem("MedReminder")
}
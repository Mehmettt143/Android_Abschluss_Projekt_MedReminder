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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoTextItem(
    title: String,
    size: Int = 18,
    color: Color = Color.Black,
    textAlign: TextAlign,
    textDecoration: TextDecoration = TextDecoration.None
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = title,
            fontSize = size.sp,
            style = MaterialTheme.typography.bodyLarge,
            color = color,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            textAlign = textAlign,
            textDecoration = textDecoration
        )
    }


}

@Preview(showBackground = true)
@Composable
private fun InfoTextPreview() {
    // Use Theme here
    //InfoTextItem()
}
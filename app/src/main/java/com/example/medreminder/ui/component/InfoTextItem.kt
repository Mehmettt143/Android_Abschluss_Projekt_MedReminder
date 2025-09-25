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
import com.example.medreminder.ui.theme.BottombarBlue

@Composable
fun InfoTextItem(
    title: String,
    size: Int = 18,
    color: Color = Color.Black,
    textAlign: TextAlign,
    textDecoration: TextDecoration = TextDecoration.None,
    modifier: Modifier = Modifier
) {
    //Box für den Informationstext
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        //Text für die Information
        Text(
            text = title,
            fontSize = size.sp,
            style = MaterialTheme.typography.titleMedium,
            color = color,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            textAlign = textAlign,
            textDecoration = textDecoration
        )
    }


}

//Preview für den Informationstext
@Preview(showBackground = true)
@Composable
private fun InfoTextItemPreview() {
    InfoTextItem(
        title = "MedikamentenInformationen",
        size = 18,
        color = BottombarBlue,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}
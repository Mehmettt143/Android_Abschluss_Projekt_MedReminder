package com.example.medreminder.ui.component.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medreminder.R
import com.example.medreminder.ui.theme.BottombarBlue

@Composable
fun DrugSearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(stringResource(R.string.label_for_placeholder_search_drug))
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = BottombarBlue,
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.search),
                contentDescription = "Search",
                tint = Color.Unspecified,
            )
        },
        shape = RoundedCornerShape(25.dp),
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
private fun DrugSearchBarPreview() {

}
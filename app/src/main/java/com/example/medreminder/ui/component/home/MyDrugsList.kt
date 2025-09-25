package com.example.medreminder.ui.component.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medreminder.data.remote.firebase.model.DrugReminder

@Composable
fun MyDrugsList(
    modifier: Modifier = Modifier,
    myDrugs: List<DrugReminder>,
    onRemoveClick: (DrugReminder) -> Unit
) {

    //LazyColumn für die Liste der eigenen Medikamente
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(myDrugs) { drug ->
            //Elemente der Liste
            MyDrugCard(
                drug = drug,
                onRemoveClick = { onRemoveClick(drug) }


            )
        }
    }
}




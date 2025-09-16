package com.example.medreminder.data.remote.drugapi.model

import com.squareup.moshi.Json

data class DrugLabelResponse(
    @Json(name = "results")
    val results: List<Drug>
)
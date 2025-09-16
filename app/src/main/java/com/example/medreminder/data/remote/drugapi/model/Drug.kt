package com.example.medreminder.data.remote.drugapi.model

import com.squareup.moshi.Json

data class Drug(
    val id: String,
    @Json(name = "openfda")
    val openfda: OpenFda?,
    @Json(name = "indications_and_usage")
    val indicationsAndUsage: List<String>?,
    @Json(name = "dosage_and_administration")
    val dosageAndAdministration: List<String>?,
    @Json(name = "warnings")
    val warnings: List<String>?,
    @Json(name = "adverse_reactions")
    val adverseReactions: List<String>?
)





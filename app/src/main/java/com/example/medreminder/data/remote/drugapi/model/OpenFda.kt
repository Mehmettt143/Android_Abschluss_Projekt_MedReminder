package com.example.medreminder.data.remote.drugapi.model

import com.squareup.moshi.Json

data class OpenFda(
    @Json(name = "brand_name")
    val brandName: List<String>?,
    @Json(name = "generic_name")
    val genericName: List<String>?,
    @Json(name = "manufacturer_name")
    val manufacturerName: List<String>?
)




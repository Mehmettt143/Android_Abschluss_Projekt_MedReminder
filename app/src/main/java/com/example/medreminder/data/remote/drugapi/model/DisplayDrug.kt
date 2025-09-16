package com.example.medreminder.data.remote.drugapi.model


data class DisplayDrug(
    val id: String,
    val brandName: String?,
    val genericName: String?,
    val manufacturerName: String?,
    val indicationsAndUsage: String?,
    val dosageAndAdministration: String?,
    val warnings: String?,
    val adverseReactions: String?
)

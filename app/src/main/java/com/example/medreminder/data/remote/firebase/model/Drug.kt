package com.example.medreminder.data.remote.firebase.model

data class Drug(
    val id: String="",
    val brandName: String="", //Marke
    val genericName: String?="", //gebräuchlicher Name
    val manufacturerName: String?="", //Hersteller
    val indicationsAndUsage: String?="", // Anwendungsgebiete und Anwendung
    val dosageAndAdministration: String?="", //Dosierung und Verabreichung
    val warnings: String?="", //Warnungen
    val adverseReactions: String?="", // Nebenwirkungen
)

package com.example.medreminder.data.remote.firebase.model

data class User(
    val id: String = "", // hier wollen wir die user id von der Firebase Authentication übernehmen
    val email: String = "",
    val username: String= "",
)
package com.example.medreminder.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreenRoute


@Serializable
object FavoriteScreenRoute


@Serializable
object AddScreenRoute


@Serializable
object SettingsScreenRoute

@Serializable
data class DetailScreenRoute(val drugId: String)



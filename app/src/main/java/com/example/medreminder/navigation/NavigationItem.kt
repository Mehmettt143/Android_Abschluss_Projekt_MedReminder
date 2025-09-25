package com.example.medreminder.navigation

import com.example.medreminder.R


enum class NavigationItem(
    val route: Any,
    val label: Int,
    val icon: Int
) {
    Home(
        route = HomeScreenRoute,
        label = R.string.tab_label_home,
        icon = R.drawable.home
    ),
    Favorites(
        route = FavoriteScreenRoute,
        label = R.string.tab_label_favorite,
        icon = R.drawable.star
    ),
    Add(
        route = AddScreenRoute,
        label = R.string.tab_label_add_medicament,
        icon = R.drawable.add
    ),
    Setting(
        route = SettingsScreenRoute,
        label = R.string.tab_label_setting,
        icon = R.drawable.settings
    )


}
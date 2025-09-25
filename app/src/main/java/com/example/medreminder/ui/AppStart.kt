package com.example.medreminder.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medreminder.navigation.AddScreenRoute
import com.example.medreminder.navigation.FavoriteScreenRoute
import com.example.medreminder.navigation.HomeScreenRoute
import com.example.medreminder.navigation.SettingsScreenRoute
import com.example.medreminder.navigation.DetailScreenRoute
import com.example.medreminder.navigation.NavigationItem
import com.example.medreminder.ui.screen.AuthScreen
import com.example.medreminder.ui.screen.DetailScreen
import com.example.medreminder.ui.screen.DrugAddScreen
import com.example.medreminder.ui.screen.FavoriteScreen
import com.example.medreminder.ui.screen.HomeScreen
import com.example.medreminder.ui.screen.SettingsScreen
import com.example.medreminder.ui.theme.BottombarBlue
import com.example.medreminder.ui.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel


@SuppressLint("ResourceAsColor")
@Composable
fun AppStart(
    authViewModel: AuthViewModel = koinViewModel(),
) {
    val navController = rememberNavController()
    var selectedTabItem by remember { mutableStateOf(NavigationItem.Home) }

    //Prüft ob der Benutzer eingeloggt ist
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    if (!isLoggedIn) {
        //zeigt den Loginscreen wen der Benutzer noch nicht eingeloggt ist
        AuthScreen()
    } else {
        //hier wird alles erstellt und die Homescreen wird angezeigt
        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = BottombarBlue
                ) {
                    NavigationItem.entries.forEach { tabItem ->
                        NavigationBarItem(
                            selected = tabItem == selectedTabItem,
                            onClick = {
                                selectedTabItem = tabItem
                                navController.navigate(tabItem.route)
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(tabItem.icon),
                                    contentDescription = stringResource(tabItem.label),
                                    modifier = Modifier,
                                    tint = Color.Unspecified
                                )
                            },
                            label = {
                                Text(stringResource(tabItem.label))
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedTextColor = Color.White,
                                unselectedTextColor = Color.White,
                                selectedIconColor = Color.White
                            )
                        )
                    }
                }
            }
        ) { innerPadding ->
            //Navigationshost:  die Verwaltung der Bildschirmnavigation
            NavHost(
                navController = navController,
                startDestination = selectedTabItem.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<HomeScreenRoute> {
                    HomeScreen(
                        navController = navController
                    )
                }
                composable<FavoriteScreenRoute> {
                    //Favoritenbildschirm mit Funktion zum Entfernen und Navigieren
                    FavoriteScreen(
                        navController = navController
                    )
                }
                composable<AddScreenRoute> {
                    //Bildschirm zum Hinzufügen neuer Medikamente
                    DrugAddScreen()
                }
                composable<SettingsScreenRoute> {
                    //Settingsscreen: Export und Notification und Logout
                    SettingsScreen()
                }
                composable<DetailScreenRoute> {
                    //Detailscreen für ein spezifisches Medikament
                    DetailScreen(
                        navController = navController
                    )
                }
            }
        }
    }
}
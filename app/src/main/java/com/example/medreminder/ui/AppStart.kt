package com.example.medreminder.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.example.medreminder.ui.viewmodel.DetailViewModel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("ResourceAsColor")
@Composable
fun AppStart(
    authViewModel: AuthViewModel = viewModel()
) {
    val navController = rememberNavController()
    var selectedTabItem by remember { mutableStateOf(NavigationItem.Home) }

    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    if (!isLoggedIn) {
        AuthScreen()
    } else {
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
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = HomeScreenRoute,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable<HomeScreenRoute> {
                    HomeScreen(
                        navController = navController,

                        )
                }
                composable<FavoriteScreenRoute> {
                    FavoriteScreen()
                }
                composable<AddScreenRoute> {
                    DrugAddScreen()
                }
                composable<SettingsScreenRoute> {
                    SettingsScreen()
                }
                composable<DetailScreenRoute> {
                    DetailScreen(
                        viewModel = DetailViewModel(it.savedStateHandle),
                        navController = navController
                    )
                }
            }
        }
    }
}
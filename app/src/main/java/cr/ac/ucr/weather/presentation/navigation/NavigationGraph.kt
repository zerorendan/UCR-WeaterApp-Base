package cr.ac.ucr.weather.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cr.ac.ucr.weather.presentation.home.HomeViewModel
import cr.ac.ucr.weather.presentation.home.HomeScreen


@Composable
fun NavigationGraph(
    startDestination: String = NavigationScreen.HomeScreen.route,
    homeViewModel: HomeViewModel,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = startDestination
        ) {
            composable(NavigationScreen.HomeScreen.route) {
                HomeScreen(homeViewModel) {

                }
            }

        }
    }
}
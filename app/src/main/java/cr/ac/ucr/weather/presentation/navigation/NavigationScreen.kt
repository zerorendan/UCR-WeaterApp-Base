package cr.ac.ucr.weather.presentation.navigation

import cr.ac.ucr.weather.core.utils.constant.NavigationRoutes

sealed class NavigationScreen(val route: String) {
    object HomeScreen : NavigationScreen(NavigationRoutes.homeScreen)
}

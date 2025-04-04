package shared.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import shared.ui.screen.ChartsScreen.ChartsScreen
import shared.ui.screen.HomeScreen.HomeContent
import shared.ui.screen.ProfileScreen.ProfileScreen

@Composable
fun HomeNavigator(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Routes.NavDestination.Home.route,
        modifier = modifier
    ) {
        composable(Routes.NavDestination.Home.route) { HomeContent() }
        composable(Routes.NavDestination.Profile.route) { ProfileScreen() }
        composable(Routes.NavDestination.Charts.route) { ChartsScreen() }
    }
}

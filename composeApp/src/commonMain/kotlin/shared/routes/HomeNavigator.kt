package shared.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.koin.compose.viewmodel.koinViewModel
import shared.ui.screen.ChartsScreen.ChartsScreen
import shared.ui.screen.HomeScreen.CategoryScreen
import shared.ui.screen.ProfileScreen.ProfileScreen
import shared.viewModel.CategoryViewModel

@Composable
fun HomeNavigator(
    userId: Int,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.NavDestination.Home.route,
        modifier = modifier
    ) {
        composable(Routes.NavDestination.Home.route) {
            val categoryViewModel = koinViewModel<CategoryViewModel>()
            CategoryScreen(
                categoryViewModel,
                userId = userId,
                onContinue = { }
            )
        }
        composable(Routes.NavDestination.Profile.route) { ProfileScreen() }
        composable(Routes.NavDestination.Charts.route) { ChartsScreen() }
    }
}

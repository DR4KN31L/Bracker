package shared.routes

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import org.koin.compose.KoinContext
import org.koin.core.context.KoinContext
import shared.ui.screen.HomeScreen.HomeScreen
import shared.ui.screen.LoginScreen.CreateAccountScreen
import shared.ui.screen.LoginScreen.LoginScreen
import shared.ui.screen.PresentationScreen.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val animationDuration = 300

    NavHost(navController = navController, startDestination = Routes.SplashScreen) {
        composable<Routes.SplashScreen>{
            SplashScreen(onTimeOut = { navController.navigate(Routes.Login()){
                popUpTo(Routes.SplashScreen) { inclusive = true }
            }
            })
        }
        composable<Routes.Login>(
            enterTransition = { slideInHorizontally (initialOffsetX = { it }, animationSpec = tween(animationDuration)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(animationDuration)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(animationDuration)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(animationDuration)) }
        ) { backStackEntry ->
            val createAccountPage = backStackEntry.toRoute<Routes.Login>()
            LoginScreen(username = createAccountPage?.username, onNavigateToCreateAccount = { navController.navigate(Routes.CreateAccount) })
        }
        composable<Routes.CreateAccount>(
            enterTransition = { slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(animationDuration)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(animationDuration)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(animationDuration)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(animationDuration)) }
        ) {
            CreateAccountScreen(onNavigateBack = { username ->
                navController.navigate(Routes.Login(username)) {
                    popUpTo(Routes.CreateAccount) { inclusive = true }
                }
            })
        }
        composable<Routes.Home> {
            HomeScreen(onNavigateBack = {navController.navigate(Routes.Login){
                    popUpTo(Routes.Login) { inclusive = true }
                }
            })
        }
    }
}
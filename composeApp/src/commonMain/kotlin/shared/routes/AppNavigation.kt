package shared.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import shared.ui.screen.HomeScreen.HomeScreen
import shared.ui.screen.LoginScreen.CreateAccountScreen
import shared.ui.screen.LoginScreen.LoginScreen
import shared.ui.screen.PresentationScreen.SplashScreen
import shared.utils.animatedNavExitTransitions
import shared.utils.animatedNavTransitions
import shared.utils.animatedPopEnter
import shared.utils.animatedPopExit


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.SplashScreen) {
        composable<Routes.SplashScreen> {
            SplashScreen(onTimeOut = {
                navController.navigate(Routes.Login()) {
                    popUpTo(Routes.SplashScreen) { inclusive = true }
                }
            })
        }
        composable<Routes.Login>(
            enterTransition = animatedNavTransitions(),
            exitTransition = animatedNavExitTransitions(),
            popEnterTransition = animatedPopEnter(),
            popExitTransition = animatedPopExit()
        ) { backStackEntry ->
            val createAccountPage = backStackEntry.toRoute<Routes.Login>()
            LoginScreen(
                usernameParam = createAccountPage.username,
                onNavigateToCreateAccount = { navController.navigate(Routes.CreateAccount) },
                onNavigateHome = { user -> navController.navigate(Routes.Home(user.id)) }
            )
        }
        composable<Routes.CreateAccount>(
            enterTransition = animatedNavTransitions(),
            exitTransition = animatedNavExitTransitions(),
            popEnterTransition = animatedPopEnter(),
            popExitTransition = animatedPopExit()
        ) {
            CreateAccountScreen(onNavigateBack = { username ->
                navController.navigate(Routes.Login(username)) {
                    popUpTo(Routes.CreateAccount) { inclusive = true }
                }
            })
        }
        composable<Routes.Home>(
            enterTransition = animatedNavTransitions(),
            exitTransition = animatedNavExitTransitions(),
            popEnterTransition = animatedPopEnter(),
            popExitTransition = animatedPopExit()
        ) { backStackEntry ->
            val route = backStackEntry.toRoute<Routes.Home>()
            HomeScreen(userId = route.userId)
        }
    }
}
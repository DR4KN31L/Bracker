package shared.routes

import bracker.composeapp.generated.resources.Res
import bracker.composeapp.generated.resources.chart
import bracker.composeapp.generated.resources.home
import bracker.composeapp.generated.resources.profile
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource

sealed class Routes {
    @Serializable
    data object SplashScreen : Routes()

    @Serializable
    data class Login(val username: String? = null) : Routes()

    @Serializable
    data object CreateAccount : Routes()

    @Serializable
    data class Home(val userId: Int) : Routes()

    sealed class NavDestination(val title: String, val route: String, val drawableRes: DrawableResource) :
        Routes() {
        data object Home :
            NavDestination(
                title = "Home",
                route = "CategoryScreen",
                drawableRes = Res.drawable.home
            )

        data object Profile :
            NavDestination(
                title = "Profile",
                route = "ProfileScreen",
                drawableRes = Res.drawable.profile
            )

        data object Charts :
            NavDestination(
                title = "Charts",
                route = "ChartsScreen",
                drawableRes = Res.drawable.chart
            )
    }
}
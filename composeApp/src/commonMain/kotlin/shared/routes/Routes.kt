package shared.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AreaChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object SplashScreen : Routes()

    @Serializable
    data class Login(val username: String? = null) : Routes()

    @Serializable
    data object CreateAccount : Routes()

    @Serializable
    data class Home(val userId: Int) : Routes()

    sealed class NavDestination(val title: String, val route: String, val icon: ImageVector) :
        Routes() {
        data object Home :
            NavDestination(title = "Home", route = "CategoryScreen", icon = Icons.Filled.Home)

        data object Profile : NavDestination(
            title = "Profile",
            route = "ProfileScreen",
            icon = Icons.Filled.AccountCircle
        )

        data object Charts :
            NavDestination(title = "Charts", route = "ChartsScreen", icon = Icons.Filled.AreaChart)
    }
}
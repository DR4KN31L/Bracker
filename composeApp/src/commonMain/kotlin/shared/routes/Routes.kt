package shared.routes

import kotlinx.serialization.Serializable

sealed class Routes{
    @Serializable
    data object SplashScreen : Routes()
    @Serializable
    data class Login(val username: String? = null) : Routes()
    @Serializable
    data object CreateAccount: Routes()
    @Serializable
    data object Home : Routes()
}
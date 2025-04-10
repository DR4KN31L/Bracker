package shared.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoggedInUser(
    val id: Int,
    val name: String,
    val lastname: String,
)

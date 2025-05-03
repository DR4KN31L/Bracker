package shared.utils

import androidx.compose.ui.graphics.Color

val NAME_LASTNAME_REGEX = Regex("^[A-Za-z]+(?: [A-Za-z]+)*\$")
val USERNAME_REGEX = Regex("^[a-zA-Z0-9._-]{5,20}\$")
val PASSWORD_REGEX = Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*()_+\\-={}|\\[\\]:;\"'<>,.?/]).{8,}\$")
val LIGHT_GREEN = Color(0xFFD9F0DC)
val DARK_GREEN = Color(0xFF06402D)
val SOFT_GREEN = Color(0xFFADD5A3)
val SHINY_GREEN = Color(0xFF72C865)
val SOFT_YELLOW = Color(0xFFF9D952)
val SOFT_YELLOW_50 = SOFT_YELLOW.copy(alpha = 0.5f)
val GOLD = Color(0xFFFFCA02)
val DARK_ORANGE = Color(0xFF18401)
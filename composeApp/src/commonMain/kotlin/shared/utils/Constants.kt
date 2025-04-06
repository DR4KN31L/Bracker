package shared.utils

val NAME_LASTNAME_REGEX = Regex("^[A-Za-z]+(?: [A-Za-z]+)*\$")
val USERNAME_REGEX = Regex("^[a-zA-Z0-9._-]{5,20}\$")
val PASSWORD_REGEX = Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*()_+\\-={}|\\[\\]:;\"'<>,.?/]).{8,}\$")

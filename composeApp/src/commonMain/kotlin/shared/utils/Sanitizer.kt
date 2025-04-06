package shared.utils

class Sanitizer {

    companion object {
        private const val MAX_LENGTH = 20

        fun validateInputLength(input: String): Boolean {
            return input.length <= MAX_LENGTH
        }

        private fun validateNLString(input: String) : Boolean {
            return NAME_LASTNAME_REGEX.matches(input)
        }

        private fun validateUsername(input: String): Boolean {
            return USERNAME_REGEX.matches(input)
        }

        private fun validatePassword(input: String): Boolean {
            return PASSWORD_REGEX.matches(input)
        }
        fun validateAllInputs(
            name: String,
            lastname: String,
            username: String,
            password: String
        ): String? {
            if (name.isBlank() || lastname.isBlank() || username.isBlank() || password.isBlank()) {
                return "Todos los campos son obligatorios"
            }
            if (!validateInputLength(name) || !validateNLString(name)) {
                return "Nombre inválido"
            }
            if (!validateInputLength(lastname) || !validateNLString(lastname)) {
                return "Apellido inválido"
            }
            if (!validateUsername(username)) {
                return "Usuario inválido"
            }
            if (!validatePassword(password)) {
                return "Contraseña inválida"
            }
            return null
        }

        fun validateLogin(username: String,password: String): String? {
            if (username.isBlank() || password.isBlank()){
                return "Todos los campos son obligatorios"
            }
            return null
        }
    }
}
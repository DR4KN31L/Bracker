package shared.utils

class Sanitizer {
    companion object {
        private const val MAX_LENGTH = 20

        fun validateInputLength(input: String): Boolean {
            return input.length <= MAX_LENGTH
        }

        fun verifyVoidInput(input: String) : Boolean {
            return input.length > 5
        }
    }
}
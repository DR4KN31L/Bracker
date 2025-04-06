package shared.utils

import at.favre.lib.crypto.bcrypt.BCrypt

object PasswordHasher {
    fun hash(password: String): String {
        return BCrypt.withDefaults()
            .hashToString(12, password.toCharArray())
    }

    fun verify(password: String, hashed: String): Boolean {
        return BCrypt.verifyer()
            .verify(password.toCharArray(), hashed)
            .verified
    }
}
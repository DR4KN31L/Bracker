package shared.data.repository

import shared.data.localDb.BrackerDB
import shared.data.model.LoggedInUser
import shared.utils.PasswordHasher

class AuthManagerRepository(private val database: BrackerDB) {
    suspend fun loginUser(username: String, password: String): LoggedInUser? {
        val userHelper = database.authDAO().loginUser(username) ?: return null
        val isValidPassword = PasswordHasher.verify(password, userHelper.password)
        return if (isValidPassword) {
            LoggedInUser(
                id = userHelper.id,
                name = userHelper.name,
                lastname = userHelper.lastname
            )
        } else {
            return null
        }
    }
}
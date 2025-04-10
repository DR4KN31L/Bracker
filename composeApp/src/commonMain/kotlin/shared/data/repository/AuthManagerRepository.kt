package shared.data.repository

import shared.data.dao.AuthDAO
import shared.data.model.LoggedInUser
import shared.utils.PasswordHasher

class AuthManagerRepository(private val authDAO: AuthDAO) {
    suspend fun loginUser(username: String, password: String): LoggedInUser? {
        val userHelper = authDAO.loginUser(username) ?: return null
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